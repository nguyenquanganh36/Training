package com.anhnq.storedemo.controller;

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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO signupUser) throws Exception {
        UserEntity userRegister = new UserEntity();
        userRegister.setUsername(signupUser.getUsername());
        userRegister.setPassword(passwordEncoder.encode(signupUser.getPassword()));
        userRegister.setEmail(signupUser.getEmail());

        Set<String> strRoles = signupUser.getRoles();
        Set<RoleEntity> roles = new HashSet<>();

        if(strRoles == null){
            RoleEntity userRole = roleRepository.findByRolename("ROLE_USER");
            if(userRole != null){
                roles.add(userRole);
            }
        } else {
            strRoles.forEach( role -> {
                        switch (role) {
                            case "admin":
                                RoleEntity adminRole = roleRepository.findByRolename("ROLE_ADMIN");
                                if(adminRole != null){
                                    roles.add(adminRole);
                                }
                                break;
                            case "user":
                                RoleEntity userRole = roleRepository.findByRolename("ROLE_USER");
                                if(userRole != null){
                                    roles.add(userRole);
                                }
                                break;
                            default:
                                RoleEntity exRole = roleRepository.findByRolename("ROLE_EX");
                                if(exRole != null){
                                    roles.add(exRole);
                                }
                        }
                    });
        }

        userRegister.setRoles(roles);

        return ResponseEntity.ok(userRepository.save(userRegister));
    }

    @GetMapping("/listUser")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(iUserService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUserById(@RequestBody UserDTO user, @PathVariable("id") long id){
        return ResponseEntity.ok(iUserService.findUserById(id));
    }

//    @PutMapping("/user/{id}")
//    public ResponseEntity<?> updateUser(@RequestBody UserDTO user, @PathVariable("id") long id){
//        return ResponseEntity.ok(iUserService.findUserById(id));
//    }
}
