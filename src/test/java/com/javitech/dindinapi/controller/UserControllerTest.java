package com.javitech.dindinapi.controller;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.javitech.dindinapi.model.User;
import com.javitech.dindinapi.service.user.UserService;
import com.javitech.dindinapi.service.utils.http.HttpServiceImpl;
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
@Import(HttpServiceImpl.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private User user;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        user = new User();
        user.setId(userId);
        user.setName("Test User");
        user.setIsApproved(false);
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
        doThrow(
            new IllegalArgumentException(
                "User with ID " + userId + " does not exist"
            )
        )
            .when(userService)
            .approveUser(userId);

        mockMvc
            .perform(put("/api/user/approve/{id}", userId))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value("User not found"));
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
        doThrow(
            new IllegalArgumentException(
                "User with ID " + userId + " does not exist"
            )
        )
            .when(userService)
            .rejectUser(userId);

        mockMvc
            .perform(put("/api/user/reject/{id}", userId))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value("User not found"));
    }
}
