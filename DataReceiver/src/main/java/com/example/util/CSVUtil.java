package com.example.util;

import com.example.repository.entity.DataRecord;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    public static List<DataRecord> readFromCSV(String filePath) throws IOException {
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

    public static void writeToCSV(String filePath, List<DataRecord> dataList) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (DataRecord data : dataList) {
                writer.write(data.getId() + "," + data.getData() + "," + data.isProcessed() + "\n");
            }
        }
    }
}
