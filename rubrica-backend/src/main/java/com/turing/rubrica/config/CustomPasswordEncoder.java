package com.turing.rubrica.config;

import com.turing.rubrica.service.CustomUserDetails;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;


public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        String pwHash = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(rawPassword.toString().getBytes());
            pwHash = Base64.getEncoder().encodeToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CustomUserDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pwHash;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
