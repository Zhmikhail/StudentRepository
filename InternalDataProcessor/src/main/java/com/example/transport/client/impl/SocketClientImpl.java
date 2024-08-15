package com.example.transport.client.impl;

import com.example.transport.dto.request.DataRequest;
import com.example.transport.dto.response.DataResponse;
import com.example.transport.client.SocketClient;
import java.io.*;
import java.net.Socket;

public class SocketClientImpl implements SocketClient {
    private final String host;
    private final int port;

    public SocketClientImpl(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public DataResponse sendData(DataRequest dataRequest) throws IOException {
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(dataRequest);
            return (DataResponse) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Error in response deserialization", e);
        }
    }
}
