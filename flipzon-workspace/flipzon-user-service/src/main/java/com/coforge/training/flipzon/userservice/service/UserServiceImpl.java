package com.coforge.training.flipzon.userservice.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.coforge.training.flipzon.userservice.model.User;
import com.coforge.training.flipzon.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    // Stores OTP and expiry time for each email
    private final Map<String, OtpDetails> otpStorage = new ConcurrentHashMap<>();

    public UserServiceImpl(
            UserRepository userRepository,
            JavaMailSender mailSender) {

        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @Override
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setPhone(updatedUser.getPhone());
        user.setGender(updatedUser.getGender());
        user.setDateOfBirth(updatedUser.getDateOfBirth());
        user.setAddress(updatedUser.getAddress());
        user.setCity(updatedUser.getCity());
        user.setState(updatedUser.getState());
        user.setPincode(updatedUser.getPincode());

        return userRepository.save(user);
    }

    // ==============================
    // SEND OTP
    // ==============================

    @Override
    public void sendOtp(String email) {

        // Check whether email exists
        userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Email not registered"));

        // Generate 6 digit OTP
        String otp = String.valueOf(
                100000 + new Random().nextInt(900000)
        );

        // OTP expires after 5 minutes
        LocalDateTime expiryTime =
                LocalDateTime.now().plusMinutes(5);

        // Store OTP
        otpStorage.put(
                email,
                new OtpDetails(otp, expiryTime)
        );

        // Create email
        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Flipzon Password Reset OTP");

        message.setText(
                "Hello,\n\n"
                + "Your Flipzon password reset OTP is: "
                + otp
                + "\n\n"
                + "This OTP is valid for 5 minutes.\n\n"
                + "If you did not request a password reset, "
                + "please ignore this email.\n\n"
                + "Regards,\n"
                + "Flipzon Team"
        );

        // Send email
        mailSender.send(message);
    }

    // ==============================
    // RESET PASSWORD
    // ==============================

    @Override
    public void forgotPassword(
            String email,
            String otp,
            String newPassword) {

        // Check user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Email not registered"));

        // Get OTP details
        OtpDetails otpDetails =
                otpStorage.get(email);

        if (otpDetails == null) {
            throw new RuntimeException(
                    "Please request a new OTP");
        }

        // Check expiry
        if (LocalDateTime.now()
                .isAfter(otpDetails.expiryTime)) {

            otpStorage.remove(email);

            throw new RuntimeException(
                    "OTP has expired. Please request a new OTP");
        }

        // Check OTP
        if (!otpDetails.otp.equals(otp)) {

            throw new RuntimeException(
                    "Invalid OTP");
        }

        // Update password
        user.setPassword(newPassword);

        userRepository.save(user);

        // Remove OTP after successful reset
        otpStorage.remove(email);
    }

    // ==============================
    // OTP DETAILS CLASS
    // ==============================

    private static class OtpDetails {

        private final String otp;
        private final LocalDateTime expiryTime;

        public OtpDetails(
                String otp,
                LocalDateTime expiryTime) {

            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }
}