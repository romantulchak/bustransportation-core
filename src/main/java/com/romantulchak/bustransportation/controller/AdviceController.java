package com.romantulchak.bustransportation.controller;

import com.romantulchak.bustransportation.exception.*;
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
    @ExceptionHandler(DirectionNotFoundException.class)
    public ResponseEntity<?> handleDirectionNotFoundException(DirectionNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BusAlreadyExistException.class)
    public ResponseEntity<?> handleBusAlreadyExistException(BusAlreadyExistException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DirectionAlreadyExistException.class)
    public ResponseEntity<?> handleDirectionAlreadyExistException(DirectionAlreadyExistException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<?> handleTripNotFoundException(TripNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OccupiedSeatException.class)
    public ResponseEntity<?> handleOccupiedSeatException(OccupiedSeatException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<?> handleEmailAlreadyTakenException(EmailAlreadyTakenException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest webRequest){
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
