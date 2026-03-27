package com.javitech.dindinapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.javitech.dindinapi.model.Permission;
import com.javitech.dindinapi.model.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    void shouldSaveRoleWithPermissions() {
        // Arrange
        Permission permission = new Permission();
        permission.setName("Read User");
        permission.setDescription("Allows reading users");
        permission.setSlugName("read-user");
        Permission savedPermission = permissionRepository.save(permission);

        Role role = new Role();
        role.setName("Admin");
        role.setDescription("Administrator role");
        role.setPermissions(List.of(savedPermission));

        // Act
        Role savedRole = roleRepository.save(role);

        // Assert
        assertThat(savedRole.getId()).isNotNull();
        assertThat(savedRole.getPermissions()).hasSize(1);
        assertThat(savedRole.getPermissions().get(0).getName()).isEqualTo("Read User");
    }
}
