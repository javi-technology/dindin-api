package com.javitech.dindinapi.service;

import com.javitech.dindinapi.model.Quote;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuoteService {
    Quote save(Quote quote);
    List<Quote> findAll();
    Optional<Quote> findById(UUID id);
    Quote update(Quote user);
    void deleteById(UUID id);
}
