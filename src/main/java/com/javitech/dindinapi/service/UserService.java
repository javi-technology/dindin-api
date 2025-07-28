package com.javitech.dindinapi.service;

import com.javitech.dindinapi.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(UUID id);
    User update(User user);
    void deleteById(UUID id);
}
