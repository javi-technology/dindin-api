package com.javitech.dindinapi.controller;

import com.javitech.dindinapi.model.User;
import com.javitech.dindinapi.service.user.UserService;
import com.javitech.dindinapi.service.utils.http.HttpService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpService httpService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
            userService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        if (user.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
            userService.save(user)
        );
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        if (user.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User userUpdated = userService.update(user);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            httpService.createResponse("User deleted successfully.", null)
        );
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveUser(@PathVariable UUID id) {
        try {
            userService.approveUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                httpService.createResponse("User approved successfully.", null)
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                httpService.createResponse("User not found", null)
            );
        }
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectUser(@PathVariable UUID id) {
        try {
            userService.rejectUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                httpService.createResponse("User rejected successfully.", null)
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                httpService.createResponse("User not found", null)
            );
        }
    }

    @GetMapping("/approved")
    public ResponseEntity<List<User>> findByApproved() {
        return ResponseEntity.status(HttpStatus.OK).body(
            userService.findByApproved()
        );
    }
}
