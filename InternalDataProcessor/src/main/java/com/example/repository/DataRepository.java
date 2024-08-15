package com.example.repository;

import com.example.repository.entity.Data;
import java.io.IOException;
import java.util.List;

public interface DataRepository {
    void save(Data data) throws IOException;
    List<Data> findAll() throws IOException;
    void update(Data data) throws IOException;
}
