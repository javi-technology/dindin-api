package com.javitech.dindinapi.controller;

import com.javitech.dindinapi.model.Quote;
import com.javitech.dindinapi.model.Wallet;
import com.javitech.dindinapi.service.QuoteService;
import com.javitech.dindinapi.service.WalletService;
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
    private QuoteService quoteService;

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

        if (verifyQuotes(wallet.getQuotes())) {
            resp.put("message", "Quote não cadastrada.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }

        Wallet walletCreated = walletService.save(wallet);
        resp.put("message", "Wallet cadastrada com sucesso.");
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

        if (verifyQuotes(wallet.getQuotes())) {
            resp.put("message", "Quote não cadastrada.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }

        Wallet walletUpdated = walletService.update(wallet);
        resp.put("message", "Wallet atualizada com sucesso.");
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

    private boolean verifyQuotes(List<UUID> quotes) {
        for (UUID quoteUuid : quotes) {
            Optional<Quote> existingQuote = quoteService.findById(quoteUuid);
            if (existingQuote.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
