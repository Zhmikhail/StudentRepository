package com.example.transport.mapper;

import com.example.repository.entity.DataRecord;
import com.example.transport.dto.request.DataRequest;
import com.example.transport.dto.response.DataResponse;

public class DataMapper {
    public static DataRequest toRequest(DataRecord data) {
        return new DataRequest(data.getId(), data.getData());
    }

    public static DataResponse toResponse(boolean success, String message) {
        return new DataResponse(success, message);
    }
}

