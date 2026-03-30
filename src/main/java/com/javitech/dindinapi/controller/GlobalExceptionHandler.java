package com.javitech.dindinapi.controller;

import com.javitech.dindinapi.exception.ResourceNotFoundException;
import com.javitech.dindinapi.service.utils.http.HttpService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private HttpService httpService;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<
        HashMap<String, Object>
    > handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            httpService.createResponse(ex.getMessage(), null)
        );
    }
}
