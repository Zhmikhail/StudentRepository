package com.example.transport.server;

import com.example.service.DataReceiverService;
import com.example.repository.entity.DataRecord;
import com.example.transport.dto.request.DataRequest;
import com.example.transport.dto.response.DataResponse;
import com.example.transport.mapper.DataMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class DataReceiverServer {
    private final int port;
    private final DataReceiverService dataService;

    public DataReceiverServer(int port, DataReceiverService dataService) {
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
                    DataRecord data = new DataRecord(UUID.randomUUID().toString(), request.getData());
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
