package com.petshopadmin.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

@Data
public class ValidationException extends Exception implements Supplier<ValidationException> {

    Collection<String> messages;

    HttpStatus statusCode;

    public ValidationException(String message, HttpStatus statusCode) {
        this.setMessages(Collections.singletonList(message));
        this.setStatusCode(statusCode);
    }

    public ValidationException(Collection<String> messages, HttpStatus statusCode) {
        this.setMessages(messages);
        this.setStatusCode(statusCode);
    }

    @Override
    public ValidationException get() {
        return this;
    }
}
