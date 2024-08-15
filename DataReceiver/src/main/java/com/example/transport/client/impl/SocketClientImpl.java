package com.example.transport.client.impl;

import com.example.transport.dto.request.DataRequest;
import com.example.transport.dto.response.DataResponse;
import com.example.transport.client.SocketClient;

import java.io.*;
import java.net.Socket;

public class SocketClientImpl implements SocketClient {
    private String host;
    private int port;

    public SocketClientImpl(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public DataResponse send(DataRequest request) throws IOException {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(request.toJson());
            String response = in.readLine();
            return DataResponse.fromJson(response);
        }
    }
}

