package com.example.transport.dto.response;

import java.io.Serializable;

public class DataResponse implements Serializable {
    private boolean success;
    private String message;

    public DataResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
