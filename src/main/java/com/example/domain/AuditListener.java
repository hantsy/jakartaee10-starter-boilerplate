package com.example.domain;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class AuditListener {

    @PrePersist
    public void prePersist(Object entity) {

    }

    @PreUpdate
    public void preUpdate(Object entity) {

    }
}
