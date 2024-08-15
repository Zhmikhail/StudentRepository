package repository.impl;

import repository.DataRepository;
import repository.entity.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepositoryImpl implements DataRepository {
    private final String filePath;

    public DataRepositoryImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(Data data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data.getId() + "," + data.getData() + "," + data.isProcessed());
            writer.newLine();
        }
    }

    @Override
    public List<Data> findAll() throws IOException {
        List<Data> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Data data = new Data(parts[0], parts[1]);
                    data.setProcessed(Boolean.parseBoolean(parts[2]));
                    dataList.add(data);
                }
            }
        }
        return dataList;
    }

    @Override
    public void update(Data data) throws IOException {
        List<Data> dataList = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Data d : dataList) {
                if (d.getId().equals(data.getId())) {
                    d = data;
                }
                writer.write(d.getId() + "," + d.getData() + "," + d.isProcessed());
                writer.newLine();
            }
        }
    }
}
