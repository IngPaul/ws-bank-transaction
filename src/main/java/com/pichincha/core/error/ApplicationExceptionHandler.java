package com.pichincha.core.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(BankApiException.class)
    public ResponseEntity<?> handleBankApiException(BankApiException bankApiException){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message", bankApiException.getMessage());
        errorMap.put("status", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.ok(errorMap);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(Exception exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message", exception.getMessage());
        errorMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return ResponseEntity.ok(errorMap);
    }
}