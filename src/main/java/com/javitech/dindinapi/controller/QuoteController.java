package com.javitech.dindinapi.controller;

import com.javitech.dindinapi.model.Quote;
import com.javitech.dindinapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/quote")
public class QuoteController {
    @Autowired
    private QuoteService quoteService;

    @GetMapping
    public ResponseEntity<List<Quote>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(quoteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Quote>> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(quoteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Quote> create(@RequestBody Quote quote){
        if (quote.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(quoteService.save(quote));
    }

    @PutMapping
    public ResponseEntity<Quote> update(@RequestBody Quote quote){
        if (quote.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Quote quoteUpdated = quoteService.update(quote);
        return ResponseEntity.status(HttpStatus.OK).body(quoteUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        HashMap<String, Object> response = new HashMap<>();
        quoteService.deleteById(id);
        response.put("message", "Quote deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
