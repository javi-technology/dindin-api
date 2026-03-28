package com.javitech.dindinapi.service.asset;

import com.javitech.dindinapi.model.Asset;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssetService {
    Asset save(Asset asset);
    List<Asset> findAll();
    Optional<Asset> findById(UUID id);
    List<Asset> findAllById(Iterable<UUID> ids);
    Asset update(Asset asset);
    void deleteById(UUID id);
}
