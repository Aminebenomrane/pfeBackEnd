package com.example.pfebackend.service.Impl;

import com.example.pfebackend.auth.UserDetailsUpdateRequest;
import com.example.pfebackend.config.EncryptionUtils;
import com.example.pfebackend.models.ChangePasswordRequest;
import com.example.pfebackend.models.User;
import com.example.pfebackend.repository.UserRepository;
import com.example.pfebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final EncryptionUtils encryptionUtils;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, EncryptionUtils encryptionUtils) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.encryptionUtils = encryptionUtils;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> optionalUser = repository.findByEmail(email);

        optionalUser.ifPresent(this::decryptUserFields);

        return optionalUser;
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords do not match");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);
    }

    @Override
    public void updateProfileImage(MultipartFile image, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (image != null && !image.isEmpty()) {
            try {
                String imageUrl = saveImage(image);
                user.setProfileImageUrl(imageUrl);
                repository.save(user);
            } catch (IOException e) {
                throw new IllegalStateException("Failed to save profile image: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Image file is empty or null");
        }
    }

    @Override
    public void updateProfileAndDetails(MultipartFile image, UserDetailsUpdateRequest userDetails, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        user.setFirstname(encryptionUtils.encrypt(userDetails.getFirstname()));
        user.setLastname(encryptionUtils.encrypt(userDetails.getLastname()));
        user.setEmail(encryptionUtils.encrypt(userDetails.getEmail()));
        user.setNomEntreprise(userDetails.getNomEntreprise());

        if (image != null && !image.isEmpty()) {
            try {
                String imageUrl = saveImage(image);
                user.setProfileImageUrl(imageUrl);
            } catch (IOException e) {
                throw new IllegalStateException("Failed to save profile image: " + e.getMessage());
            }
        }

        repository.save(user);
    }

    public User getUserById(int userId) {
        Optional<User> userOptional = repository.findById(userId);
        User user = userOptional.orElse(null);

        if (user != null) {
            decryptUserFields(user);
        }

        return user;
    }

    public User saveUser(User user) {
        // Ne pas utiliser le chiffrement
        return repository.save(user);
    }


    // New method for decrypting User fields
    private void decryptUserFields(User user) {
        String decryptedFirstname = encryptionUtils.decrypt(user.getFirstname());
        String decryptedLastname = encryptionUtils.decrypt(user.getLastname());
        String decryptedEmail = encryptionUtils.decrypt(user.getEmail());

        if (decryptedFirstname != null) {
            user.setFirstname(decryptedFirstname);
        }
        if (decryptedLastname != null) {
            user.setLastname(decryptedLastname);
        }
        if (decryptedEmail != null) {
            user.setEmail(decryptedEmail);
        }

        // Decrypt other fields as needed


        // Decrypt other fields as needed
    }

    private String saveImage(MultipartFile image) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        String uploadDir = "src/main/resources/static/profile-images/";

        Path uploadPath = Path.of(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }
}
