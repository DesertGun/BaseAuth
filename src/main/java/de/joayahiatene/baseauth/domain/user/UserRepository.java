package de.joayahiatene.baseauth.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
    Optional<User> findOneByUsername(String username);
    boolean existsUserByUsername(String username);
}
