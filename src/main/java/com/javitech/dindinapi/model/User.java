package com.javitech.dindinapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private String country;
    private String state;
    private String city;
    private String whatsapp;
    private Date birthday;
    private Date createdAt;
    private Date updatedAt;
}
