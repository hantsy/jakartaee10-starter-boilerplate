package com.example.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
public class AbstractEntity<ID extends Serializable> implements Auditable, Persistable<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private ID id;

    private Instant createdAt;

    private Instant lastModifiedAt;

    @Override
    public Instant getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public void setCreatedAt(Instant instant) {
        this.createdAt = instant;
    }

    @Override
    public Instant getLastModifiedAt() {
        return this.lastModifiedAt;
    }

    @Override
    public void setLastModifiedAt(Instant instant) {
        this.lastModifiedAt = instant;
    }

    @Override
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
