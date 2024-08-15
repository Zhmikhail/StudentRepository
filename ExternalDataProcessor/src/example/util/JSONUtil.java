package example.util;

import example.repository.entity.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JSONUtil {
    public static List<Data> readFromJSON(String filePath) throws IOException {
        List<Data> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming the JSON format is simple
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

    public static void writeToJSON(String filePath, List<Data> dataList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Data data : dataList) {
                writer.write(data.getId() + "," + data.getData() + "," + data.isProcessed() + "\n");
            }
        }
    }
}


