package com.petersen.academitdb.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petersen.academitdb.dto.UserDTO;
import com.petersen.academitdb.exceptions.UserExistExepcetion;
import com.petersen.academitdb.exceptions.UserNotExistException;
import com.petersen.academitdb.model.User;
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
    public ResponseEntity<?> add(@RequestBody User user){
        try {
            return new ResponseEntity<>(this.servicio.alta(user), HttpStatus.CREATED);
        } catch (UserExistExepcetion e){
            return ResponseEntity.badRequest().body("Ya existe el usuario");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error interno desconocido");
        }
    }

//    @PostMapping
//    public ResponseEntity<UserDTO> add(@RequestBody UserDTO userDTO) {
//
//
//        ObjectMapper om = new ObjectMapper();
//        User user = om.convertValue(userDTO, User.class);
//
//        User userDB = this.servicio.alta(user);
//
//        UserDTO finalUser = om.convertValue(userDB, UserDTO.class);
//        return ResponseEntity.ok(finalUser);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                    @RequestBody UserDTO userDTO) {

        try {
            ObjectMapper om = new ObjectMapper();
            User user = om.convertValue(userDTO, User.class);
            User userUpdate = this.servicio.updateUser(id, user);
            UserDTO dto = om.convertValue(userUpdate, UserDTO.class);
            return ResponseEntity.ok(dto);
        } catch (UserNotExistException e){
            return ResponseEntity.notFound().build();
        }



//        Optional<User> userOptional = this.servicio.getById(id);
//
//        if(!userOptional.isPresent()) {
//            return ResponseEntity
//                    .notFound().build();
//        } else {
//            ResponseEntity.ok();
//            User userUpdate = userOptional.get();
//            userUpdate.setName(user.getName());
//            userUpdate.setLastname(user.getLastname());
//            userUpdate.setPassword(user.getPassword());
//            return new ResponseEntity<>(this.servicio.alta(userUpdate), HttpStatus.OK);
//        }
    }

}
