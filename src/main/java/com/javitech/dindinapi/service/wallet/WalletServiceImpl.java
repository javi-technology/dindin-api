package com.javitech.dindinapi.service.wallet;

import com.javitech.dindinapi.model.Wallet;
import com.javitech.dindinapi.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Optional<Wallet> findById(UUID id) {
        return walletRepository.findById(id);
    }

    @Override
    public Wallet update(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public void deleteById(UUID id) {
        walletRepository.deleteById(id);
    }
}
