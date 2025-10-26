package com.javitech.dindinapi.service.quote;

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
        return quoteRepository.save(quote);
    }

    @Override
    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    @Override
    public Optional<Quote> findById(UUID id) {
        return quoteRepository.findById(id);
    }

    @Override
    public Quote update(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public void deleteById(UUID id) {
        quoteRepository.deleteById(id);
    }
}
