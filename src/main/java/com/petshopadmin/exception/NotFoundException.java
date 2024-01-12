package com.petshopadmin.exception;

import lombok.Data;

import java.util.function.Supplier;

@Data
public class NotFoundException extends Exception implements Supplier<NotFoundException> {

    String message;

    public NotFoundException(String message) {
        this.setMessage(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public NotFoundException get() {
        return this;
    }

}