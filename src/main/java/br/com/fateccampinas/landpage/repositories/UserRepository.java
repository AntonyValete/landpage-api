package br.com.fateccampinas.landpage.repositories;

import br.com.fateccampinas.landpage.domain.user.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String> {
    UserDetails findByEmail(String email);
}
