package com.petersen.academitdb.services;

import com.petersen.academitdb.dominio.User;
import com.petersen.academitdb.repositories.UserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "app", name = "bean.name", havingValue = "bd")
public class UserService implements IUserService{

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User alta(User user) {
        return this.repository.save(user);
    }

    @Override
    public Iterable<User> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<User> getByName(String name, String lastname) {
        return this.repository.getByName(name, lastname);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return this.repository.findByEmail(email);
    }
}
