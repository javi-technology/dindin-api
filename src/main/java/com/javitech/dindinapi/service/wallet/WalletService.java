package com.javitech.dindinapi.service.wallet;

import com.javitech.dindinapi.model.Wallet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletService {
    Wallet save(Wallet wallet);
    List<Wallet> findAll();
    Optional<Wallet> findById(UUID id);
    Wallet update(Wallet wallet);
    void deleteById(UUID id);
}
