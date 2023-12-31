package com.elastic.springbootelasticsearch.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {


    public static ResponseEntity createHttpResponse(String message, Object data, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                new Response(
                        httpStatus.value(),
                        httpStatus.toString(),
                        message,
                        data),
                httpStatus);
    }


}