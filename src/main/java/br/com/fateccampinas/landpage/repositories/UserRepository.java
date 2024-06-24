package br.com.fateccampinas.landpage.repositories;

import br.com.fateccampinas.landpage.domain.user.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserDAO, String> {
    UserDetails findByEmail(String email);
}
