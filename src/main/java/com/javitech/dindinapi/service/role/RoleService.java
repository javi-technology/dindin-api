package com.javitech.dindinapi.service.role;

import com.javitech.dindinapi.model.Role;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    Role save(Role role);

    List<Role> findAll();

    Optional<Role> findById(UUID id);

    Role update(Role role);

    void deleteById(UUID id);
}
