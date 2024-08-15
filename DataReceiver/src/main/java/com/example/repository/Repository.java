package com.example.repository;

import  com.example.repository.entity.DataRecord;

import java.io.IOException;
import java.util.List;

public interface Repository {
    void save(DataRecord record) throws IOException;

    List<DataRecord> findAll() throws IOException;

    void update(DataRecord record) throws IOException;
}
