package com.javitech.dindinapi.controller;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javitech.dindinapi.model.Permission;
import com.javitech.dindinapi.service.permission.PermissionService;
import com.javitech.dindinapi.service.utils.http.HttpServiceImpl;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> findAll() {
        HttpServiceImpl httpService = new HttpServiceImpl();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(httpService.createResponse("Permissions retrieved successfully.", permissionService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> findById(@PathVariable UUID id) {
        HttpServiceImpl httpService = new HttpServiceImpl();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(httpService.createResponse("Permission retrieved successfully.", permissionService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> create(@RequestBody Permission permission) {
        HttpServiceImpl httpService = new HttpServiceImpl();

        if (permission.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Permission permissionCreated = permissionService.save(permission);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(httpService
                        .createResponse("Permission created successfully.", permissionCreated));
    }

    @PutMapping
    public ResponseEntity<HashMap<String, Object>> update(@RequestBody Permission permission) {
        HttpServiceImpl httpService = new HttpServiceImpl();

        if (permission.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (permissionService.findById(permission.getId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Permission permissionUpdated = permissionService.update(permission);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(httpService
                        .createResponse("Permission updated successfully.", permissionUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        HttpServiceImpl httpService = new HttpServiceImpl();
        Optional<Permission> permission = permissionService.findById(id);

        if (permission.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(httpService.createResponse("Permission with ID " + id + " does not exist.", null));
        }

        permissionService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(httpService.createResponse("Permission deleted successfully.", null));
    }
}
