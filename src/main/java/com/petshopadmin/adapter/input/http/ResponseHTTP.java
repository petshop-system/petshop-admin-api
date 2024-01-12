package com.petshopadmin.adapter.input.http;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ResponseHTTP {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Object result;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime date;

}
