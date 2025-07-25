package com.javitech.dindinapi.controller;

import com.javitech.dindinapi.service.FirestoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final FirestoreService firestoreService;

    public UserController(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

    @PostMapping
    public ResponseEntity<String> createUser() {
        try {
            String userId = "testeID";
            Map<String, String> userData = Map.of(
                    "name", "Vinicius",
                    "email", "vinicius.kremer@gmail.com"
            );
            String documentId = firestoreService.save("users", userId, userData);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error creating user: " + e.getMessage());
        }
        return ResponseEntity.ok("User created successfully");
    }
}
