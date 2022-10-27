package com.anhnq.storedemo.service;

import com.anhnq.storedemo.dto.UserDTO;

import java.util.List;

public interface IUser {
    List<UserDTO> findAll();
    UserDTO findUserById(long id);
    UserDTO save(UserDTO userDTO);
    void delete(long[] ids);
}
