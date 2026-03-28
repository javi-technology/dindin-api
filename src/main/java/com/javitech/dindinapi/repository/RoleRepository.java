package com.javitech.dindinapi.repository;

import com.javitech.dindinapi.model.Role;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, UUID> {}
