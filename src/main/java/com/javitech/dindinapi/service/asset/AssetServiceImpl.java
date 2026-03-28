package com.javitech.dindinapi.service.asset;

import com.javitech.dindinapi.model.Asset;
import com.javitech.dindinapi.repository.AssetRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public Asset save(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    @Override
    public Optional<Asset> findById(UUID id) {
        return assetRepository.findById(id);
    }

    @Override
    public List<Asset> findAllById(Iterable<UUID> ids) {
        return assetRepository.findAllById(ids);
    }

    @Override
    public Asset update(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public void deleteById(UUID id) {
        assetRepository.deleteById(id);
    }
}
