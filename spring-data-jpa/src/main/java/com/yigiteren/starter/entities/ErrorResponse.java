package com.yigiteren.starter.entities;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private String path;
    private long timestamp;
}
