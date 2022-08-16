package com.petersen.academitdb.controllers;

import com.petersen.academitdb.dominio.User;
import com.petersen.academitdb.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserService servicio;

    public UserController(UserService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getAll(){
        List<User> users = (List<User>) this.servicio.getAll();
        UserResponse respose = new UserResponse();
        if(users.isEmpty()){
            respose.setStatus(false);
            respose.setMessage("No hay datos de usuarios cargados");
        } else {
            respose.setStatus(true);
            respose.setData(users);
        }
        return ResponseEntity.ok(respose);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        Optional<User> user = this.servicio.getById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name")
    public ResponseEntity<User> getByName(@RequestParam String name,
                                       @RequestParam String lastname){
        Optional<User> user = this.servicio.getByName(name, lastname);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email){
        Optional<User> user = this.servicio.getByEmail(email);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user){
        return new ResponseEntity<>(this.servicio.alta(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,
                                    @RequestBody User user) {
        Optional<User> userOptional = this.servicio.getById(id);

        if(!userOptional.isPresent()) {
            return ResponseEntity
                    .notFound().build();
        } else {
            ResponseEntity.ok();
            User userUpdate = userOptional.get();
            userUpdate.setName(user.getName());
            userUpdate.setLastname(user.getLastname());
            userUpdate.setPassword(user.getPassword());
            return new ResponseEntity<>(this.servicio.alta(userUpdate), HttpStatus.OK);
        }
    }

}
