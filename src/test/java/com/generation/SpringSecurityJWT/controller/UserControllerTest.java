package com.generation.SpringSecurityJWT.controller;

import com.generation.SpringSecurityJWT.model.Post;
import com.generation.SpringSecurityJWT.model.User;
import com.generation.SpringSecurityJWT.service.PostService;
import com.generation.SpringSecurityJWT.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserControllerTest {




    @Test
    @DisplayName("Pruebas UserController save")
    void saveTest(){
        //Verificar que el UserService haya sido llamado el metodo save

        UserService userService = mock (UserService.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        UserController userController = new UserController(userService,bCryptPasswordEncoder);

        User user = new User();
        user.setName("Ximena");
        user.setSurname("Monroy");
        user.setAddress("CDMX");
        user.setUsername("XimMon");
        user.setPassword("lamas");

        when(userService.save(any(User.class))).thenReturn(user);

        User result = userController.save(user);
        verify(userService,times(1)).save(any(User.class));
        verify(bCryptPasswordEncoder,atLeastOnce()).encode("lamas");
        assertEquals (user.getName(),result.getName());
    }

    @Test
    @DisplayName("Pruebas en getUser")
    void getUserTest(){
        UserService userService = mock (UserService.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        UserController userController = new UserController(userService,bCryptPasswordEncoder);

        User user = new User();
        user.setId(1L);
        user.setName("Ximena");
        user.setSurname("Monroy");
        user.setAddress("CDMX");
        user.setUsername("XimMon");
        user.setPassword("lamas");

        when(userService.save(any(User.class))).thenReturn(user);

        User result = userController.save(user);
        verify(userService,times(1)).save(any(User.class));

        assertEquals (user.getId(),result.getId());
    }

}