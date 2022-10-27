package com.anhnq.storedemo.controller;

import com.anhnq.storedemo.converter.UserConverter;
import com.anhnq.storedemo.dto.UserDTO;
import com.anhnq.storedemo.entity.RoleEntity;
import com.anhnq.storedemo.entity.UserEntity;
import com.anhnq.storedemo.repository.RoleRepository;
import com.anhnq.storedemo.repository.UserRepository;
import com.anhnq.storedemo.service.IUser;
import com.anhnq.storedemo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IUser iUserService;

    @Autowired
    UserConverter userConverter;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO signupUser) throws Exception {
        return ResponseEntity.ok(iUserService.save(signupUser));
    }

    @GetMapping("/listUser")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(iUserService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") long id){
        return ResponseEntity.ok(iUserService.findUserById(id));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user, @PathVariable("id") long id){
        user.setId(id);
        return ResponseEntity.ok(iUserService.save(user));
    }

    @DeleteMapping("/user")
    public void deleteById(@RequestBody long[] ids){
       iUserService.delete(ids);
    }
}
