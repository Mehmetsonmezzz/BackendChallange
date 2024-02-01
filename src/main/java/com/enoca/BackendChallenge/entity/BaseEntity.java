package com.enoca.BackendChallenge.entity;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}