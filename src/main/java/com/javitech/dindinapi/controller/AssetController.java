package com.javitech.dindinapi.controller;

import com.javitech.dindinapi.model.Asset;
import com.javitech.dindinapi.service.asset.AssetService;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public ResponseEntity<List<Asset>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(assetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> findById(@PathVariable UUID id) {
        return assetService
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asset> create(@RequestBody Asset asset) {
        if (asset.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(assetService.save(asset));
    }

    @PutMapping
    public ResponseEntity<Asset> update(@RequestBody Asset asset) {
        if (asset.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (assetService.findById(asset.getId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Asset assetUpdated = assetService.update(asset);
        return ResponseEntity.status(HttpStatus.OK).body(assetUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Optional<Asset> asset = assetService.findById(id);
        HashMap<String, Object> response = new HashMap<>();

        if (asset.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asset not found");
        }

        assetService.deleteById(id);
        response.put("message", "Asset deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
