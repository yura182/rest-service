package com.yura.resthw.service;

import com.yura.resthw.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto add(UserDto userDto);

    UserDto findById(Integer id);

    Page<UserDto> findAll(Pageable pageable);

    UserDto update(UserDto userDto, Integer id);

    void delete(Integer id);
}
