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

@RestController
@RequestMapping("user")
public class UserController {

    private final static String ID_PATH = "/{id}";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ID_PATH)
    public UserDto getUser(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping()
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }

    @PutMapping(ID_PATH)
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        return userService.update(userDto, id);
    }

    @DeleteMapping(ID_PATH)
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }
}
