package com.javitech.dindinapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javitech.dindinapi.model.Permission;
import com.javitech.dindinapi.service.permission.PermissionService;

@WebMvcTest(PermissionController.class)
@AutoConfigureMockMvc(addFilters = false)
class PermissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PermissionService permissionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Permission permission;
    private UUID permissionId;

    @BeforeEach
    void setUp() {
        permissionId = UUID.randomUUID();
        permission = new Permission();
        permission.setId(permissionId);
        permission.setName("TEST_PERMISSION");
        permission.setDescription("Test Description");
        permission.setSlugName("test-permission");
    }

    @Test
    @DisplayName("Should create a permission and return 201 Created")
    void shouldCreatePermission() throws Exception {
        when(permissionService.save(any(Permission.class))).thenReturn(permission);

        mockMvc.perform(post("/api/permission")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(permission)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Permission created successfully."))
                .andExpect(jsonPath("$.data.name").value("TEST_PERMISSION"));
    }

    @Test
    @DisplayName("Should return 400 Bad Request when creating permission with null name")
    void shouldReturn400WhenNameIsNull() throws Exception {
        permission.setName(null);

        mockMvc.perform(post("/api/permission")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(permission)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return all permissions and return 200 OK")
    void shouldReturnAllPermissions() throws Exception {
        when(permissionService.findAll()).thenReturn(List.of(permission));

        mockMvc.perform(get("/api/permission"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Permissions retrieved successfully."))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @DisplayName("Should return a permission by ID and return 200 OK")
    void shouldReturnPermissionById() throws Exception {
        when(permissionService.findById(permissionId)).thenReturn(Optional.of(permission));

        mockMvc.perform(get("/api/permission/{id}", permissionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Permission retrieved successfully."));
    }

    @Test
    @DisplayName("Should update a permission and return 200 OK")
    void shouldUpdatePermission() throws Exception {
        when(permissionService.findById(permissionId)).thenReturn(Optional.of(permission));
        when(permissionService.update(any(Permission.class))).thenReturn(permission);

        mockMvc.perform(put("/api/permission")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(permission)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Permission updated successfully."));
    }

    @Test
    @DisplayName("Should return 404 Not Found when updating non-existing permission")
    void shouldReturn404WhenUpdatingNonExisting() throws Exception {
        when(permissionService.findById(permissionId)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/permission")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(permission)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should delete a permission and return 200 OK")
    void shouldDeletePermission() throws Exception {
        when(permissionService.findById(permissionId)).thenReturn(Optional.of(permission));

        mockMvc.perform(delete("/api/permission/{id}", permissionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Permission deleted successfully."));
    }

    @Test
    @DisplayName("Should return 404 Not Found when deleting non-existing permission")
    void shouldReturn404WhenDeletingNonExisting() throws Exception {
        when(permissionService.findById(permissionId)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/permission/{id}", permissionId))
                .andExpect(status().isNotFound());
    }
}
