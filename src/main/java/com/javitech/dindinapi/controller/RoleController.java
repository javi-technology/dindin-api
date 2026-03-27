package com.javitech.dindinapi.controller;

import com.javitech.dindinapi.model.Role;
import com.javitech.dindinapi.service.role.RoleService;
import com.javitech.dindinapi.service.utils.http.HttpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> findAll() {
        HttpServiceImpl httpService = new HttpServiceImpl();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(httpService.createResponse("Roles retrieved successfully.", roleService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> findById(@PathVariable UUID id){
        HttpServiceImpl httpService = new HttpServiceImpl();
        Optional<Role> role = roleService.findById(id);
        if (role.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(httpService.createResponse("Role with ID " + id + " does not exist.", null));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(httpService.createResponse("Role retrieved successfully.", role.get()));
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> create(@RequestBody Role role){
        HttpServiceImpl httpService = new HttpServiceImpl();

        if (role.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Role roleCreated = roleService.save(role);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(httpService
                        .createResponse("Role created successfully.", roleCreated));
    }

    @PutMapping
    public ResponseEntity<HashMap<String, Object>> update(@RequestBody Role role){
        HttpServiceImpl httpService = new HttpServiceImpl();

        if (role.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (roleService.findById(role.getId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Role roleUpdated = roleService.update(role);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(httpService
                        .createResponse("Role updated successfully.", roleUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        HttpServiceImpl httpService = new HttpServiceImpl();
        Optional<Role> role = roleService.findById(id);

        if (role.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(httpService.createResponse("Role with ID " + id + " does not exist.", null));
        }

        roleService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(httpService.createResponse("Role deleted successfully.", null));
    }
}
