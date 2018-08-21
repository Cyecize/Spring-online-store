package com.cyecize.skatefixers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Something was not working properly")
public class InternalException extends RuntimeException {

    public InternalException(String message){
        super(message);
    }
}
