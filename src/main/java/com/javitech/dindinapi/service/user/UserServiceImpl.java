package com.javitech.dindinapi.service.user;

import com.javitech.dindinapi.model.User;
import com.javitech.dindinapi.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return userRepository.findByIsApprovedTrue();
    }

    @Override
    public void approveUser(UUID id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with ID " + id + " does not exist"));
        user.setIsApproved(true);
        userRepository.save(user);
    }

    @Override
    public void rejectUser(UUID id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with ID " + id + " does not exist"));
        user.setIsApproved(false);
        userRepository.save(user);
    }
}
