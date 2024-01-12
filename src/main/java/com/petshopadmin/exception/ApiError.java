package com.petshopadmin.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.petshopadmin.adapter.input.http.ResponseHTTP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiError extends ResponseHTTP {

    public ApiError(Map<String, Object> message) {
        super(message.get("message").toString(),
                null,
                null,
                LocalDateTime.now());
    }

}