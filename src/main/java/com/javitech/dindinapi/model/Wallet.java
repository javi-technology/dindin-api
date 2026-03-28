package com.javitech.dindinapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "wallet_assets", joinColumns = @JoinColumn(name = "wallet_id"))
    @Column(name = "asset_uuid", nullable = false)
    private List<UUID> assets;

    @ElementCollection
    @CollectionTable(name = "wallet_frozen_assets", joinColumns = @JoinColumn(name = "wallet_id"))
    @Column(name = "frozen_asset_uuid", nullable = false)
    private List<UUID> frozenAssets;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
