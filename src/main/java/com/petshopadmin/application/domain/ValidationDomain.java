package com.petshopadmin.application.domain;

public class ValidationDomain {
    private final String field;
    private final String message;

    public ValidationDomain(String field, String message) {
        this.field = field;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ValidationError{" + "field: '" + field + '\'' +  ", message: '" + message + '\'' + '}';
    }
}
