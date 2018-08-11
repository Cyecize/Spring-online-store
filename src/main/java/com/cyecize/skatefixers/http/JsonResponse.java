package com.cyecize.skatefixers.http;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class JsonResponse {
    private String message;

    private HttpStatus status;

    private Map<String, String> body;

    public JsonResponse(String message){
        this.message = message;
        this.status = HttpStatus.OK;
        this.body = new HashMap<>();
    }

    public JsonResponse(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }

    public JsonResponse(String message, HttpStatus status, Map<String, String> body) {
        this(message, status);
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, String> getBody() {
        return body;
    }
}
