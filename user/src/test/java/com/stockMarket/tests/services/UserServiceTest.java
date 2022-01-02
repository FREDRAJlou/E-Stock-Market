package com.stockMarket.tests.services;

import com.stockMarket.UserManagementApplication;
import com.stockMarket.entities.User;
import com.stockMarket.repositories.UserRepository;
import com.stockMarket.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserManagementApplication.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepositoryMock;

    @Test
    void testGetUserByName(){
         when(userRepositoryMock.findByName(anyString())).thenReturn(new User());
        Assertions.assertNotNull(this.userService.getUserByName("user"));
    }

    @Test
    void testSaveUser(){
        when(userRepositoryMock.save(any())).thenReturn(new User());
        User user = new User();
        this.userService.saveUser(user);
        verify(this.userRepositoryMock,times(1)).save(user);
    }

}
