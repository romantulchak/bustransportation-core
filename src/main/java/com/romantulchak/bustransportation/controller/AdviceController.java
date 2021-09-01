package com.romantulchak.bustransportation.controller;

import com.romantulchak.bustransportation.exception.*;
import com.romantulchak.bustransportation.model.enums.ErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.HashMap;
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
    private Map<String, Object> getBody(RuntimeException ex, ErrorCode errorCode) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("errorCode", errorCode.getValue());
        return body;
    }

    @ExceptionHandler(BusNotFoundException.class)
    public ResponseEntity<?> handleBusNotFoundException(BusNotFoundException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DirectionNotFoundException.class)
    public ResponseEntity<?> handleDirectionNotFoundException(DirectionNotFoundException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusAlreadyExistException.class)
    public ResponseEntity<?> handleBusAlreadyExistException(BusAlreadyExistException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DirectionAlreadyExistException.class)
    public ResponseEntity<?> handleDirectionAlreadyExistException(DirectionAlreadyExistException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<?> handleTripNotFoundException(TripNotFoundException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OccupiedSeatException.class)
    public ResponseEntity<?> handleOccupiedSeatException(OccupiedSeatException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<?> handleEmailAlreadyTakenException(EmailAlreadyTakenException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex, ErrorCode.USER_DATA_INCORRECT);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<?> handleCityNotFoundException(RouteNotFoundException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex, ErrorCode.USER_DATA_INCORRECT);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<?> handleUsernameAlreadyTakenException(UsernameAlreadyTakenException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex, ErrorCode.USER_DATA_INCORRECT);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserTokenExpiredException.class)
    public ResponseEntity<?> handleUserTokenExpiredException(UserTokenExpiredException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex, ErrorCode.USER_TOKEN_EXPIRED);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<?> handleInvocationTargetException(DisabledException ex, WebRequest webRequest) {
        UserAccountNotActivatedException userAccountNotActivatedException = new UserAccountNotActivatedException();
        Map<String, Object> body = getBody(userAccountNotActivatedException, ErrorCode.USER_DISABLED);
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SeatsAlreadyBookedException.class)
    public ResponseEntity<?> handleSeatsAlreadyBookedException(SeatsAlreadyBookedException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserTokenNotFoundException.class)
    public ResponseEntity<?> handleUserTokenNotFoundException(UserTokenNotFoundException ex, WebRequest webRequest) {
        Map<String, Object> body = getBody(ex, ErrorCode.USER_TOKEN_NOT_FOUND);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<?> handleTransactionSystemException(ConstraintViolationException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> {
            String field = error.getPropertyPath().toString();
            String message = error.getMessage();
            errors.put(field, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
