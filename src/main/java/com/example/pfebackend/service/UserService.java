package com.example.pfebackend.service;

import com.example.pfebackend.auth.UserDetailsUpdateRequest;
import com.example.pfebackend.models.ChangePasswordRequest;
import com.example.pfebackend.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserByEmail(String email);

    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    void updateProfileImage(MultipartFile image, Principal connectedUser);

    void updateProfileAndDetails(MultipartFile image, UserDetailsUpdateRequest userDetails, Principal connectedUser);
    User getUserById(int userId);
    User saveUser(User user) ;
}
