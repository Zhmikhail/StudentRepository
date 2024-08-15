package com.example.transport.client;

import com.example.transport.dto.request.DataRequest;
import com.example.transport.dto.response.DataResponse;

import java.io.IOException;

public interface SocketClient {
    DataResponse sendData(DataRequest dataRequest) throws IOException;
}
