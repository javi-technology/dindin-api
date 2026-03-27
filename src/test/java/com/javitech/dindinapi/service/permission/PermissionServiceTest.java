package com.javitech.dindinapi.service.permission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javitech.dindinapi.model.Permission;
import com.javitech.dindinapi.repository.PermissionRepository;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

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
    @DisplayName("Should save a permission successfully")
    void shouldSavePermissionSuccessfully() {
        when(permissionRepository.save(any(Permission.class))).thenReturn(permission);

        Permission savedPermission = permissionService.save(permission);

        assertThat(savedPermission).isNotNull();
        assertThat(savedPermission.getName()).isEqualTo("TEST_PERMISSION");
        verify(permissionRepository, times(1)).save(permission);
    }

    @Test
    @DisplayName("Should return a list of permissions")
    void shouldReturnListOfPermissions() {
        when(permissionRepository.findAll()).thenReturn(List.of(permission));

        List<Permission> permissions = permissionService.findAll();

        assertThat(permissions).isNotEmpty().hasSize(1);
        verify(permissionRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find a permission by ID")
    void shouldFindPermissionById() {
        when(permissionRepository.findById(permissionId)).thenReturn(Optional.of(permission));

        Optional<Permission> foundPermission = permissionService.findById(permissionId);

        assertThat(foundPermission).isPresent();
        assertThat(foundPermission.get().getId()).isEqualTo(permissionId);
        verify(permissionRepository, times(1)).findById(permissionId);
    }

    @Test
    @DisplayName("Should update a permission successfully")
    void shouldUpdatePermissionSuccessfully() {
        when(permissionRepository.save(any(Permission.class))).thenReturn(permission);

        Permission updatedPermission = permissionService.update(permission);

        assertThat(updatedPermission).isNotNull();
        verify(permissionRepository, times(1)).save(permission);
    }

    @Test
    @DisplayName("Should delete a permission by ID")
    void shouldDeletePermissionById() {
        permissionService.deleteById(permissionId);

        verify(permissionRepository, times(1)).deleteById(permissionId);
    }
}
