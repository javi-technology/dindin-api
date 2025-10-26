package com.javitech.dindinapi.repository;

import com.javitech.dindinapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
