package com.javitech.dindinapi.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.javitech.dindinapi.model.User;
import com.javitech.dindinapi.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Should find all approved users using repository method")
    void shouldFindAllApprovedUsers() {
        User user = new User();
        user.setIsApproved(true);
        List<User> expectedUsers = List.of(user);

        when(userRepository.findByIsApprovedTrue()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.findByApproved();

        assertEquals(expectedUsers, actualUsers);
        verify(userRepository).findByIsApprovedTrue();
    }
}
