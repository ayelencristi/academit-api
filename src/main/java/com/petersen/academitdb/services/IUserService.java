package com.petersen.academitdb.services;

import com.petersen.academitdb.exceptions.UserExistExepcetion;
import com.petersen.academitdb.exceptions.UserNotExistException;
import com.petersen.academitdb.model.User;

import java.util.Optional;

public interface IUserService {

    User alta(User user) throws UserExistExepcetion;
    Iterable<User> getAll();
    Optional<User> getById(Long id);
    Optional<User> getByName(String name, String lastname);
    Optional<User> getByEmail(String email);
    User updateUser(Long id, User userUpdate) throws UserNotExistException;
}
