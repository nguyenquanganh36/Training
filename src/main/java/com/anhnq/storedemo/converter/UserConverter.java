package com.anhnq.storedemo.converter;

import com.anhnq.storedemo.dto.UserDTO;
import com.anhnq.storedemo.entity.RoleEntity;
import com.anhnq.storedemo.entity.UserEntity;
import com.anhnq.storedemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserConverter {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setEmail(userDTO.getEmail());

        Set<String> strRoles = userDTO.getRoles();
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

        userEntity.setRoles(roles);
        return userEntity;
    }

    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setCreateBy(entity.getCreateBy());
        dto.setCreateDate(entity.getCreateDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        Set<String> listRole = new HashSet<>();
        for (RoleEntity roleEntity: entity.getRoles()) {
            listRole.add(roleEntity.getRolename());
        }
        dto.setRoles(listRole);
        return dto;
    }

    public UserEntity toEntity(UserDTO userDTO, UserEntity userEntity) {
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setEmail(userDTO.getEmail());

        Set<String> strRoles = userDTO.getRoles();
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

        userEntity.setRoles(roles);
        return userEntity;
    }

}
