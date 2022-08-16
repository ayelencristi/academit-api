package com.petersen.academitdb.services;

import com.petersen.academitdb.dominio.User;

import java.util.Optional;

public interface IUserService {

    User alta(User user);
    Iterable<User> getAll();
    Optional<User> getById(Long id);
    Optional<User> getByName(String name, String lastname);
    Optional<User> getByEmail(String email);
}
