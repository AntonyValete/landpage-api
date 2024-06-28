package br.com.fateccampinas.landpage.repositories;

import br.com.fateccampinas.landpage.domain.user.UserRole;
import br.com.fateccampinas.landpage.domain.user.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {
    UserDetails findByEmail(String email);
    List<UsersEntity> findByRole(UserRole role);
}
