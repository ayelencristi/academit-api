package com.petersen.academitdb.services;

import com.petersen.academitdb.exceptions.UserExistExepcetion;
import com.petersen.academitdb.exceptions.UserNotExistException;
import com.petersen.academitdb.model.User;
import com.petersen.academitdb.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User alta(User user) throws UserExistExepcetion {
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

    @Override
    public User updateUser(Long id, User userUpdate) throws UserNotExistException {
        Optional<User> OpUser = this.repository.findById(id);
        if(OpUser.isPresent()){
            User user = OpUser.get();
            user.setName(userUpdate.getName());
            user.setLastname(userUpdate.getLastname());
            if (userUpdate.getPassword() != null){
                user.setPassword(userUpdate.getPassword());
            } else {
            }
            return  this.repository.save(user);
        } else{
            throw new UserNotExistException();
        }
    }
}
