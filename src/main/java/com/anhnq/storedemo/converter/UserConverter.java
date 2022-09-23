package com.anhnq.storedemo.converter;

import com.anhnq.storedemo.dto.UserDTO;
import com.anhnq.storedemo.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
//        entity.setRoles(dto.getRoles());
        return entity;
    }

    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();

        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
//        dto.setRoles(entity.getRoles());
        return dto;
    }

}
