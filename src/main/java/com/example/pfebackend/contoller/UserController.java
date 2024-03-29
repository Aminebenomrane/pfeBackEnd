package com.example.pfebackend.contoller;


import com.example.pfebackend.auth.UserDetailsUpdateRequest;
import com.example.pfebackend.models.ChangePasswordRequest;
import com.example.pfebackend.models.User;
import com.example.pfebackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.security.Principal;
import java.util.Optional;

import static com.example.pfebackend.utils.Constants.Api_Root;


@RestController
@RequestMapping(value = Api_Root+"/users")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) throws ChangeSetPersister.NotFoundException {
        return Optional.ofNullable(userService.getUserByEmail(email)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException()));
    }
    @PostMapping("/update-profile-image")
    public ResponseEntity<?> updateProfileImage(
            @RequestParam("image") MultipartFile image,
            Principal connectedUser
    ) {
        userService.updateProfileImage(image, connectedUser);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/update-profile-and-details")
    public ResponseEntity<?> updateProfileAndDetails(
            @RequestParam("image") MultipartFile image,
            @RequestParam("userDetails") UserDetailsUpdateRequest userDetails,
            Principal connectedUser
    ) {
        userService.updateProfileAndDetails(image, userDetails, connectedUser);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/current-user")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

}