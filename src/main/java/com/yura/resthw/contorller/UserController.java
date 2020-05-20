package com.yura.resthw.contorller;

import com.yura.resthw.dto.UserDto;
import com.yura.resthw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final static String ID_PATH = "/{userId}";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ID_PATH)
    public UserDto getUser(@PathVariable Integer userId) {
        return userService.findById(userId);
    }

    @PostMapping()
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }

    @PutMapping(ID_PATH)
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId) {
        return userService.update(userDto, userId);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping(ID_PATH)
    public void deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
    }
}
