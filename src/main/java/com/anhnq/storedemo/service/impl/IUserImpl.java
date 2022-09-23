package com.anhnq.storedemo.service.impl;

import com.anhnq.storedemo.converter.UserConverter;
import com.anhnq.storedemo.dto.UserDTO;
import com.anhnq.storedemo.entity.UserEntity;
import com.anhnq.storedemo.repository.UserRepository;
import com.anhnq.storedemo.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IUserImpl implements IUser {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> listUserDTO = new ArrayList<>();
        List<UserEntity> listUserEntity = new ArrayList<>();
        for (UserEntity userEntity: listUserEntity) {
            UserDTO userDTO = userConverter.toDTO(userEntity);
            listUserDTO.add(userDTO);
        }
        return listUserDTO;
    }

    @Override
    public UserDTO findUserById(long id) {
        return userConverter.toDTO(userRepository.findById(id));
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return null;
    }

    @Override
    public void delete(long[] ids) {

    }
}
