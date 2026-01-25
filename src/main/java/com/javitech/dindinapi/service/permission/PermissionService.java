package com.javitech.dindinapi.service.permission;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.javitech.dindinapi.model.Permission;

public interface PermissionService {
    Permission save(Permission permission);

    List<Permission> findAll();

    Optional<Permission> findById(UUID id);

    Permission update(Permission permission);

    void deleteById(UUID id);
}
