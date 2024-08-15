package com.example.transport.dto.request;
import java.io.Serializable;

public class DataRequest implements Serializable {
    private String id;
    private String data;

    public DataRequest(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }
}
