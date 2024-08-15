package example.transport.mapper;

import example.repository.entity.Data;
import example.transport.dto.request.DataRequest;
import example.transport.dto.response.DataResponse;

public class DataMapper {
    public static DataRequest toRequest(Data data) {
        return new DataRequest(data.getId(), data.getData());
    }

    public static DataResponse toResponse(boolean success, String message) {
        return new DataResponse(success, message);
    }
}

