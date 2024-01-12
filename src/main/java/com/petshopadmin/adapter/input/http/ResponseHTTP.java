package com.petshopadmin.adapter.input.http;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public record ResponseHTTP(String message,
                           @JsonInclude(JsonInclude.Include.NON_NULL)
                             Object result,
                           @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
                             LocalDateTime date) {

}
