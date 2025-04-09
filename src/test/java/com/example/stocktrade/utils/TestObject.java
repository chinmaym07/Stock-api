package com.example.stocktrade.utils;

import java.time.LocalDateTime;

public class TestObject {
    private LocalDateTime timestamp;
    public TestObject() {

    }
    public TestObject(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
