package com.petersen.academitdb.repositories;

import com.petersen.academitdb.dominio.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    //Query HQL/JPQL
//"SELECT * FROM users WHERE name = ? AND lastname = ?" SQL NATIVO
    @Query("select u from User u where u.name = ?1 and u.lastname = ?2")
    Optional<User> getByName(String name, String lastname);

    //Query Method
    Optional<User> findByEmail(String email);
}
