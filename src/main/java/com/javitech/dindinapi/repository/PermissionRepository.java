package com.javitech.dindinapi.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.javitech.dindinapi.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
}
