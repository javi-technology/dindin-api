package com.javitech.dindinapi.repository;

import com.javitech.dindinapi.model.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByIsApprovedTrue();
}
