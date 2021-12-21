package com.lojadomecanico.desafio.domain.exceptions;


public class CustomException {
    private final String title;
    private final int status;
    private final long timestamp;
    private final String message;
    private final String methodService;

    public CustomException(String title, int status, long timestamp, String message, String methodService) {
        this.title = title;
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.methodService = methodService;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getMethodService() {
        return methodService;
    }
}
