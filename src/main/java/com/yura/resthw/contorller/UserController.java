package com.yura.resthw.contorller;

import com.yura.resthw.dto.UserDto;
import com.yura.resthw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findById(userId));
    }

    @PostMapping()
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.add(userDto));
    }

    @PutMapping(ID_PATH)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.update(userDto, userId));
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)
                                                             Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findAll(pageable));
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
