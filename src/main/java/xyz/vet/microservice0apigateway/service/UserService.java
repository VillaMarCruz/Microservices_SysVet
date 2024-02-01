package xyz.vet.microservice0apigateway.service;

import jakarta.transaction.Transactional;
import xyz.vet.microservice0apigateway.model.Role;
import xyz.vet.microservice0apigateway.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> listUser();

    User saveUser(User user);

    User saveUserAdmin(User user);

    void deleteUser(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Transactional
    void changeRole(Role newRole, String username);

    User findByUsernameReturnToken(String username);
}
