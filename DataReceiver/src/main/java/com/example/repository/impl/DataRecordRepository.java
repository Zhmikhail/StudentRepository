package com.example.repository.impl;

import com.example.repository.Repository;
import com.example.repository.entity.DataRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataRecordRepository implements Repository {
    private final String filePath;

    public DataRecordRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(DataRecord data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data.getId() + "," + data.getData() + "," + data.isProcessed());
            writer.newLine();
        }
    }

    @Override
    public List<DataRecord> findAll() throws IOException {
        List<DataRecord> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    DataRecord data = new DataRecord(parts[0], parts[1]);
                    data.setProcessed(Boolean.parseBoolean(parts[2]));
                    dataList.add(data);
                }
            }
        }
        return dataList;
    }

    @Override
    public void update(DataRecord data) throws IOException {
        List<DataRecord> dataList = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (DataRecord d : dataList) {
                if (d.getId().equals(data.getId())) {
                    d = data;
                }
                writer.write(d.getId() + "," + d.getData() + "," + d.isProcessed());
                writer.newLine();
            }
        }
    }
}
