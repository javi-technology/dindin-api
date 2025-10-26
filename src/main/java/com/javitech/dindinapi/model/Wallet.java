package com.javitech.dindinapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="wallets")
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
    @CollectionTable(
            name = "wallet_quotes",
            joinColumns = @JoinColumn(name = "wallet_id")
    )
    @Column(name = "quote_uuid", nullable = false)
    private List<UUID> quotes;

    @ElementCollection
    @CollectionTable(
            name = "wallet_frozen_quotes",
            joinColumns = @JoinColumn(name = "wallet_id")
    )
    @Column(name = "frozen_quote_uuid", nullable = false)
    private List<UUID> frozenQuotes;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
