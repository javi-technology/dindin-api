package com.javitech.dindinapi.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.javitech.dindinapi.exception.ResourceNotFoundException;
import com.javitech.dindinapi.model.User;
import com.javitech.dindinapi.service.user.UserService;
import com.javitech.dindinapi.service.utils.http.HttpServiceImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({ HttpServiceImpl.class, GlobalExceptionHandler.class })
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
    }

    @Test
    @DisplayName("Should find a user by ID and return 200 OK")
    void shouldFindById() throws Exception {
        User user = new User();
        user.setId(userId);
        user.setName("Test User");

        when(userService.findById(userId)).thenReturn(Optional.of(user));

        mockMvc
            .perform(get("/api/user/{id}", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(userId.toString()))
            .andExpect(jsonPath("$.name").value("Test User"));
    }

    @Test
    @DisplayName("Should return 404 Not Found when user does not exist")
    void shouldReturn404WhenUserNotFound() throws Exception {
        when(userService.findById(userId)).thenReturn(Optional.empty());

        mockMvc
            .perform(get("/api/user/{id}", userId))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should approve a user and return 200 OK")
    void shouldApproveUser() throws Exception {
        mockMvc
            .perform(put("/api/user/approve/{id}", userId))
            .andExpect(status().isOk())
            .andExpect(
                jsonPath("$.message").value("User approved successfully.")
            );
    }

    @Test
    @DisplayName(
        "Should return 404 Not Found when approving a non-existing user"
    )
    void shouldReturn404WhenApprovingNonExisting() throws Exception {
        String errorMessage = "User with ID " + userId + " does not exist";
        doThrow(new ResourceNotFoundException(errorMessage))
            .when(userService)
            .approveUser(userId);

        mockMvc
            .perform(put("/api/user/approve/{id}", userId))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value(errorMessage));
    }

    @Test
    @DisplayName("Should reject a user and return 200 OK")
    void shouldRejectUser() throws Exception {
        mockMvc
            .perform(put("/api/user/reject/{id}", userId))
            .andExpect(status().isOk())
            .andExpect(
                jsonPath("$.message").value("User rejected successfully.")
            );
    }

    @Test
    @DisplayName(
        "Should return 404 Not Found when rejecting a non-existing user"
    )
    void shouldReturn404WhenRejectingNonExisting() throws Exception {
        String errorMessage = "User with ID " + userId + " does not exist";
        doThrow(new ResourceNotFoundException(errorMessage))
            .when(userService)
            .rejectUser(userId);

        mockMvc
            .perform(put("/api/user/reject/{id}", userId))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value(errorMessage));
    }
}
