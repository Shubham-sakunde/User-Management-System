package com.userregistration.repository;

import com.userregistration.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);


    default boolean checkPassword(String email, String password) {
        Optional<User> userOptional = findByEmail(email);
        return userOptional.isPresent() && userOptional.get().getPassword().equals(password);
    }
}
