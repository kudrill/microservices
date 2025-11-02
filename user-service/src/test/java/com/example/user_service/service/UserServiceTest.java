package com.example.user_service.service;

import com.example.user_service.dto.UserDto;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("Bob");
        user.setEmail("bob@test.com");
        user.setAge(30);
        user.setCreatedAt(LocalDateTime.now());

        userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge(), user.getCreatedAt());
    }

    @Test
    void createAndGetUser() {
        when(repository.save(any(User.class))).thenReturn(user);
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        UserDto created = service.create(userDto);
        assertNotNull(created);
        assertEquals("Bob", created.name());

        UserDto fetched = service.getById(1L);
        assertEquals("Bob", fetched.name());
    }

    @Test
    void createMultipleUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<UserDto> users = service.getAll();
        assertEquals(1, users.size());
        assertEquals("Bob", users.get(0).name());
    }
}
