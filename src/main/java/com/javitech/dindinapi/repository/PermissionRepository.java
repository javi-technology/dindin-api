package com.javitech.dindinapi.repository;

import com.javitech.dindinapi.model.Permission;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {}
