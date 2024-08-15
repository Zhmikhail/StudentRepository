package example.transport.client;

import example.transport.dto.request.DataRequest;
import example.transport.dto.response.DataResponse;

import java.io.IOException;

public interface SocketClient {
    DataResponse sendData(DataRequest dataRequest) throws IOException;
}

