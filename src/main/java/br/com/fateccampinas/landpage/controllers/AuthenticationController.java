package br.com.fateccampinas.landpage.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fateccampinas.landpage.domain.user.LoginDTO;
import br.com.fateccampinas.landpage.domain.user.SignUpDTO;
import br.com.fateccampinas.landpage.services.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO userData)
            throws IllegalArgumentException, UnsupportedEncodingException {
        return authenticationService.login(userData);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignUpDTO userData) {
        return authenticationService.signup(userData);
    }
}