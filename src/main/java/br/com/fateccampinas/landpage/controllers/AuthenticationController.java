package br.com.fateccampinas.landpage.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fateccampinas.landpage.domain.user.LoginDTO;
import br.com.fateccampinas.landpage.domain.user.SignUpDTO;
import br.com.fateccampinas.landpage.domain.user.UserDAO;
import br.com.fateccampinas.landpage.infra.security.TokenService;
import br.com.fateccampinas.landpage.repositories.UserRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO userData)
            throws IllegalArgumentException, UnsupportedEncodingException {
        try {
            var auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userData.email(), userData.password())
            );
            var token = tokenService.generateToken((UserDAO) auth.getPrincipal(), userData.rememberMe());
            return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
        } catch (AuthorizationDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignUpDTO userData) {
        if (this.userRepository.existsById(userData.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(userData.password());
        UserDAO newUser = new UserDAO(userData.email(), userData.name(), userData.phoneNumber(), userData.username(), encryptedPassword, userData.role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().body("User created successfully");
    }
}
