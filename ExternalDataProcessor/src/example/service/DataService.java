package example.service;

import example.repository.DataRepository;
import example.repository.entity.Data;

import java.io.IOException;
import java.util.List;

public class DataService {
    private final DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void saveData(Data data) throws IOException {
        dataRepository.save(data);
    }

    public List<Data> getAllData() throws IOException {
        return dataRepository.findAll();
    }

    public void updateData(Data data) throws IOException {
        dataRepository.update(data);
    }
}
