package ru.korotkov.testforlab.repositors;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korotkov.testforlab.model.User;

import java.util.Optional;


public interface UserRepositor extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
