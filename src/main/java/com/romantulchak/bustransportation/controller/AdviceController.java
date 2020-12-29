package com.romantulchak.bustransportation.controller;

import com.romantulchak.bustransportation.exception.BusNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    private Map<String, Object> getBody(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return body;
    }

    @ExceptionHandler(BusNotFoundException.class)
    public ResponseEntity<?> handleBusNotFoundException(BusNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
