package com.example.service;

import com.example.repository.Repository;
import com.example.repository.entity.DataRecord;
import java.io.IOException;
import java.util.List;

public class DataReceiverService {
    private final Repository dataRepository;

    public DataReceiverService(Repository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void saveData(DataRecord data) throws IOException {
        dataRepository.save(data);
    }

    public List<DataRecord> getAllData() throws IOException {
        return dataRepository.findAll();
    }

    public void updateData(DataRecord data) throws IOException {
        dataRepository.update(data);
    }
}
