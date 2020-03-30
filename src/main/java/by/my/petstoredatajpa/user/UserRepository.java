package by.my.petstoredatajpa.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);
    Optional<User> deleteUserById(Long userID);
    Optional<User> deleteUserByUserName(String userName);
}
