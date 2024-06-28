package br.com.fateccampinas.landpage.services;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fateccampinas.landpage.domain.user.LoginDTO;
import br.com.fateccampinas.landpage.domain.user.SignUpDTO;
import br.com.fateccampinas.landpage.domain.user.UsersEntity;
import br.com.fateccampinas.landpage.infra.security.TokenService;
import br.com.fateccampinas.landpage.repositories.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> login(LoginDTO userData) throws IllegalArgumentException, UnsupportedEncodingException {
        try {
            var auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userData.email(), userData.password())
            );
            var token = tokenService.generateToken((UsersEntity) auth.getPrincipal(), userData.rememberMe());
            return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
        } catch (AuthorizationDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    public ResponseEntity<String> signup(SignUpDTO userData) {
        if (this.userRepository.existsById(userData.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
        }
        String encryptedPassword = this.passwordEncoder.encode(userData.password());
        this.userRepository.save(new UsersEntity(userData.email(),
                                                 userData.name(),
                                                 userData.phoneNumber(),
                                                 encryptedPassword,
                                                 userData.role()));
        return ResponseEntity.ok().body("User registered successfully");
    }
}