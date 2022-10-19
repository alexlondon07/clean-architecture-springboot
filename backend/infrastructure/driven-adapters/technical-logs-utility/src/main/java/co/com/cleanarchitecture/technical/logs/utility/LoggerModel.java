package co.com.cleanarchitecture.technical.logs.utility;

import java.time.LocalDateTime;
import java.util.UUID;

public class LoggerModel {
    private final String id;
    private final String name;
    private final String timestamp;
    private final String application;
    private final String service;
    private final String message;
    private String stacktrace;

    public LoggerModel(String message, String application, String service, String name) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now().toString();
        this.message = message;
        this.application = application;
        this.service = service;
        this.name = name;
    }

    public LoggerModel(String application, String service, String name, Exception ex) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now().toString();
        this.message = ex.getMessage();
        this.application = application;
        this.service = service;
        this.stacktrace = ex.getStackTrace().toString();
        this.name = name;
    }
}