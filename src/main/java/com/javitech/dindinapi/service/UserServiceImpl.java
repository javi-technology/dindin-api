package com.javitech.dindinapi.service;

import com.javitech.dindinapi.model.User;
import com.javitech.dindinapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByApproved() {
        List<User> users = userRepository.findAll();
        List<User> approvedUsers = new ArrayList<>();

        for (User user : users) {
            if (user.getIsApproved() != null && user.getIsApproved()) {
                approvedUsers.add(user);
            }
        }

        return approvedUsers;
    }

    @Override
    public void approveUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserApproval with ID " + id + " does not exist"));
        user.setIsApproved(true);
        userRepository.save(user);
        assert user.getIsApproved() == true;
    }

    @Override
    public void rejectUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserApproval with ID " + id + " does not exist"));
        user.setIsApproved(false);
        userRepository.save(user);
        assert user.getIsApproved() == false;
    }
}
