package com.yura.resthw.service.impl;

import com.yura.resthw.dao.UserRepository;
import com.yura.resthw.dto.UserDto;
import com.yura.resthw.entity.UserEntity;
import com.yura.resthw.service.UserService;
import com.yura.resthw.service.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityMapper<UserEntity, UserDto> userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EntityMapper<UserEntity, UserDto> userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto add(UserDto userDto) {
        return userMapper.mapEntityToDto(saveEntity(userDto));
    }

    @Override
    public UserDto findById(Integer id) {
        return userMapper.mapEntityToDto(getUserById(id));
    }

    @Override
    public UserDto update(UserDto userDto, Integer id) {
        userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userDto.setId(id);

        return userMapper.mapEntityToDto(saveEntity(userDto));
    }

    @Override
    public void delete(Integer id) {
        UserDto userDto = UserDto.builder().withId(id).build();

        userRepository.delete(userMapper.mapDtoToEntity(userDto));
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(userMapper::mapEntityToDto);
    }

    private UserEntity saveEntity(UserDto userDto) {
        return userRepository.save(userMapper.mapDtoToEntity(userDto));
    }

    private UserEntity getUserById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
