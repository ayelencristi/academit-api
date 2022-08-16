package com.petersen.academitdb.controllers;

import com.petersen.academitdb.dominio.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private List<User> data;
    private String message;
    private Boolean status;
}
