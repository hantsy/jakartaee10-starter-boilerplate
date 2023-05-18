package com.example.domain;

import java.io.Serializable;

public interface Persistable<ID extends Serializable> {
    ID getId();

    void setId(ID id);
}
