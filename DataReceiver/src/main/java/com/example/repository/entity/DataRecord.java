package com.example.repository.entity;

public class DataRecord {
    private String id;
    private String data;
    private boolean processed;

    public DataRecord(String id, String data) {
        this.id = id;
        this.data = data;
        this.processed = false;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
