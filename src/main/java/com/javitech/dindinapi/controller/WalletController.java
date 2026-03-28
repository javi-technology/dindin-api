package com.javitech.dindinapi.controller;

import com.javitech.dindinapi.model.Asset;
import com.javitech.dindinapi.model.Wallet;
import com.javitech.dindinapi.service.asset.AssetService;
import com.javitech.dindinapi.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private AssetService assetService;

    @GetMapping
    public ResponseEntity<List<Wallet>> findAll(){
        return ResponseEntity.ok(walletService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> findById(@PathVariable UUID id){
        return walletService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> create(@RequestBody Wallet wallet){
        HashMap<String, Object> resp = new HashMap<>();

        if (wallet.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (verifyAssets(wallet.getAssets())) {
            resp.put("message", "Asset not registered.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }

        Wallet walletCreated = walletService.save(wallet);
        resp.put("message", "Wallet created successfully.");
        resp.put("data", walletCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping
    public ResponseEntity<HashMap<String, Object>> update(@RequestBody Wallet wallet){
        HashMap<String, Object> resp = new HashMap<>();

        if (wallet.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (walletService.findById(wallet.getId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (verifyAssets(wallet.getAssets())) {
            resp.put("message", "Asset not registered.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }

        Wallet walletUpdated = walletService.update(wallet);
        resp.put("message", "Wallet updated successfully.");
        resp.put("data", walletUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        Optional<Wallet> wallet = walletService.findById(id);
        HashMap<String, Object> response = new HashMap<>();

        if (wallet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not found");
        }

        walletService.deleteById(id);
        response.put("message", "Wallet deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private boolean verifyAssets(List<UUID> assets) {
        if (assets == null) {
            return true;
        }
        if (assets.isEmpty()) {
            return false;
        }

        List<Asset> foundAssets = assetService.findAllById(assets);
        return foundAssets.size() != assets.size();
    }
}
