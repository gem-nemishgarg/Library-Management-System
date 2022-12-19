package com.library.management.service;

import com.library.management.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDt);

    UserDto updateUser(UserDto userDt, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    String deleteUser(Integer userId);
}
