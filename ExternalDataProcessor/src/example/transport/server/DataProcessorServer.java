package example.transport.server;

import example.repository.entity.Data;
import example.service.DataService;
import example.transport.dto.request.DataRequest;
import example.transport.dto.response.DataResponse;
import example.transport.mapper.DataMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class DataProcessorServer {
    private final int port;
    private final DataService dataService;

    public DataProcessorServer(int port, DataService dataService) {
        this.port = port;
        this.dataService = dataService;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    DataRequest request = (DataRequest) in.readObject();
                    Data data = new Data(UUID.randomUUID().toString(), request.getData());
                    dataService.saveData(data);

                    DataResponse response = DataMapper.toResponse(true, "Data received successfully");
                    out.writeObject(response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
