package com.javitech.dindinapi.service.permission;

import com.javitech.dindinapi.model.Permission;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionService {
    Permission save(Permission permission);

    List<Permission> findAll();

    Optional<Permission> findById(UUID id);

    Permission update(Permission permission);

    void deleteById(UUID id);
}
