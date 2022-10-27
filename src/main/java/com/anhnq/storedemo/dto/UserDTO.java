package com.anhnq.storedemo.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO extends AbstractDTO{
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
