package com.istasyon.backend.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomJson<T> {
    private int status;
    private String creationTime;
    private T content;

    public CustomJson(T content, int... status) {
        if (status.length == 0) {
            this.status = 200;
        } else {
            this.status = status[0];
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.creationTime = now.format(formatter);

        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public T getContent() {
        return content;
    }
}
