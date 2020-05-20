package com.yura.resthw.contorller;

import com.yura.resthw.dto.UserDto;
import com.yura.resthw.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

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

        UserDto actual = userController.getUser(ID);

        assertEquals(USER_DTO, actual);
    }

    @Test
    void addUser_ShouldAddUser() {
        when(userService.add(USER_DTO)).thenReturn(USER_DTO);

        UserDto actual = userController.addUser(USER_DTO);

        assertEquals(USER_DTO, actual);
    }

    @Test
    void updateUser_ShouldUpdateUser() {
        when(userService.update(USER_DTO, ID)).thenReturn(USER_DTO);

        UserDto actual = userController.updateUser(USER_DTO, ID);

        assertEquals(USER_DTO, actual);
    }

    @Test
    void deleteUser_ShouldDeleteUser() {
        userController.deleteUser(ID);

        verify(userService).delete(ID);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        List<UserDto> expected = Collections.singletonList(USER_DTO);

        when(userService.findAll()).thenReturn(expected);

        List<UserDto> actual = userController.getAllUsers();

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
