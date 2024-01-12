package com.petshopadmin.exception;

import lombok.Data;

import java.util.function.Supplier;

@Data
public class InternalServerErrorException  extends Exception implements Supplier<InternalServerErrorException> {

    String message;

    public InternalServerErrorException(String message) {
        this.setMessage(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public InternalServerErrorException get() {
        return this;
    }

}