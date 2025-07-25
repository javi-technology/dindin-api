package com.javitech.dindinapi.repository;

import com.javitech.dindinapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
