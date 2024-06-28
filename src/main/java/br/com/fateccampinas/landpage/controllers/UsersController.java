package br.com.fateccampinas.landpage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.fateccampinas.landpage.domain.user.UsersEntity;
import br.com.fateccampinas.landpage.services.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UsersEntity>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/getAllNonAdminUsers")
    public ResponseEntity<List<UsersEntity>> getAllNonAdminUsers() {
        return ResponseEntity.ok(usersService.getAllNonAdminUsers());
    }
}
