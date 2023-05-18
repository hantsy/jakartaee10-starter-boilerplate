package com.example.domain;

import java.time.Instant;

public interface Auditable {

    Instant getCreatedAt();

    void setCreatedAt(Instant instant);

    Instant getLastModifiedAt();

    void setLastModifiedAt(Instant instant);
}
