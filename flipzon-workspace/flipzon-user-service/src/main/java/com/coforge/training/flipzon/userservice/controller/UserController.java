package com.coforge.training.flipzon.userservice.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coforge.training.flipzon.userservice.model.User;
import com.coforge.training.flipzon.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody User user) {

        userService.register(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Registration Successful");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> loginData) {

        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = userService.login(email, password);

        return ResponseEntity.ok(user);
    }

    // GET USER PROFILE
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserProfile(
            @PathVariable Long id) {

        User user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    // UPDATE USER PROFILE
    @PutMapping("/{id}")
    public ResponseEntity<User> updateProfile(
            @PathVariable Long id,
            @RequestBody User user) {

        User updatedUser =
                userService.updateUser(id, user);

        return ResponseEntity.ok(updatedUser);
    }

    // SEND OTP
    @PostMapping("/forgot-password/send-otp")
    public ResponseEntity<String> sendOtp(
            @RequestBody Map<String, String> data) {

        String email = data.get("email");

        userService.sendOtp(email);

        return ResponseEntity.ok(
                "OTP sent successfully to your registered email address"
        );
    }

    // RESET PASSWORD
    @PutMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestBody Map<String, String> data) {

        String email = data.get("email");
        String otp = data.get("otp");
        String newPassword = data.get("newPassword");

        userService.forgotPassword(
                email,
                otp,
                newPassword
        );

        return ResponseEntity.ok(
                "Password updated successfully"
        );
    }
}