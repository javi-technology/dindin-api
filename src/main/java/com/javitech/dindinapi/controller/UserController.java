package com.javitech.dindinapi.controller;

import com.javitech.dindinapi.model.User;
import com.javitech.dindinapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        if (user.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<?> approveUser(@PathVariable UUID id) {
        HashMap<String, Object> response = new HashMap<>();
        userService.approveUser(id);
        response.put("message", "User approved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<?> rejectUser(@PathVariable UUID id) {
        userService.rejectUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
