package br.com.fateccampinas.landpage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fateccampinas.landpage.domain.user.UserRole;
import br.com.fateccampinas.landpage.domain.user.UsersEntity;
import br.com.fateccampinas.landpage.repositories.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    public List<UsersEntity> getAllNonAdminUsers() {
        return usersRepository.findByRole(UserRole.USER);
    }
}
