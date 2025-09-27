package com.javitech.dindinapi.service;

import com.javitech.dindinapi.model.Quote;
import com.javitech.dindinapi.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteServiceImpl implements QuoteService{
    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public Quote save(Quote quote) {
        return null;
    }

    @Override
    public List<Quote> findAll() {
        return List.of();
    }

    @Override
    public Optional<Quote> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Quote update(Quote user) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
