package com.petshopadmin.exception;


import lombok.Data;

import java.util.Map;

@Data
public class ApiError {

    private Map<String, Object> result;

    public ApiError(Map<String, Object> message) {
        this.result = message;
    }

}