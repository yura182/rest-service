package com.yura.resthw.contorller;

import com.yura.resthw.dto.UserDto;
import com.yura.resthw.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private static final UserDto USER_DTO = getUserDto();
    private static final Integer ID = 1;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Test
    void getUser_ShouldReturnUser() {
        when(userService.findById(ID)).thenReturn(USER_DTO);

        UserDto actual = userController.getUser(ID).getBody();

        assertEquals(USER_DTO, actual);
    }

    @Test
    void addUser_ShouldAddUser() {
        when(userService.add(USER_DTO)).thenReturn(USER_DTO);

        UserDto actual = userController.addUser(USER_DTO).getBody();

        assertEquals(USER_DTO, actual);
    }

    @Test
    void updateUser_ShouldUpdateUser() {
        when(userService.update(USER_DTO, ID)).thenReturn(USER_DTO);

        UserDto actual = userController.updateUser(USER_DTO, ID).getBody();

        assertEquals(USER_DTO, actual);
    }

    @Test
    void deleteUser_ShouldDeleteUser() {
        userController.deleteUser(ID);

        verify(userService).delete(ID);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        Page<UserDto> expected = new PageImpl<>(Collections.singletonList(USER_DTO));
        Pageable pageable = PageRequest.of(1, 1);

        when(userService.findAll(pageable)).thenReturn(expected);

        Page<UserDto> actual = userController.getAllUsers(pageable).getBody();

        assertEquals(expected, actual);
    }

    private static UserDto getUserDto() {
        return UserDto.builder()
                .withId(1)
                .withEmail("test@email.com")
                .withName("Name")
                .build();
    }
}
