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
import java.util.Optional;

@Service
public class IUserImpl implements IUser {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> listUserDTO = new ArrayList<>();
        List<UserEntity> listUserEntity = userRepository.findAll();
        for (UserEntity userEntity: listUserEntity) {
            UserDTO userDTO = userConverter.toDTO(userEntity);
            listUserDTO.add(userDTO);
        }
        return listUserDTO;
    }

    @Override
    public UserDTO findUserById(long id) {
        UserEntity userEntity = userRepository.getOne(id);
        return userConverter.toDTO(userEntity);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        if(userDTO.getId()!= null){
            UserEntity oldUserEntity = userRepository.getOne(userDTO.getId());
            userEntity = userConverter.toEntity(userDTO, oldUserEntity);
            System.out.print("update");
        }else {
            userEntity = userConverter.toEntity(userDTO);
            System.out.println("insert");
        }
        userEntity =userRepository.save(userEntity);
        return userConverter.toDTO(userEntity);
    }

    @Override
    public void delete(long[] ids) {
        for (long id: ids) {
            userRepository.deleteById(id);
        }
    }
}
