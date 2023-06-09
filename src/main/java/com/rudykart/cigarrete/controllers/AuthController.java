package com.rudykart.cigarrete.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rudykart.cigarrete.dto.RegisterDto;
import com.rudykart.cigarrete.entities.Role;
import com.rudykart.cigarrete.entities.User;
import com.rudykart.cigarrete.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(registerDto));
    }

    @GetMapping("/role")
    public Role[] getAllRoles() {
        return Role.values();
    }

    @GetMapping("/tes")
    public String tes() {
        return "hello";
    }
}
