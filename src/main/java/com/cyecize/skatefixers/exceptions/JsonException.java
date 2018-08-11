package com.cyecize.skatefixers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Something went wrong")
public class JsonException extends RuntimeException {

    private Map<String, String> body;

    private HttpStatus status;

    public JsonException(String message){
        super(message);
        this.body = new HashMap<>();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public JsonException(String message, Map<String, String> body){
        this(message);
        this.body = body;
    }

    public JsonException(String message, Map<String, String> body, HttpStatus status){
        this(message, body);
        this.status = status;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
