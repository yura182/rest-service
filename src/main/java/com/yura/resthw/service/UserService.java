package com.yura.resthw.service;

import com.yura.resthw.dto.UserDto;

public interface UserService {

    UserDto add(UserDto userDto);

    UserDto findById(Integer id);

    UserDto update(UserDto userDto, Integer id);

    void delete(Integer id);
}
