package com.javitech.dindinapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="\"Quote\"")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String longName;

    @Column(nullable = false, unique = true)
    private String shortName;

    @Column(nullable = false, length = 100)
    private String currency;

    @Column(nullable = false, length = 100)
    private String symbol;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private String imageUrl;
}
