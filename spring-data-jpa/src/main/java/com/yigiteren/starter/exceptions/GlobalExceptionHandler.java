package com.yigiteren.starter.exceptions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yigiteren.starter.entities.DTOs.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex , HttpServletRequest request){
            ErrorResponse response = 
            new ErrorResponse(
                404,
                ex.getMessage(),
                request.getRequestURI(),
                System.currentTimeMillis()
            );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(SchoolNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSchoolNotFound(SchoolNotFoundException ex , HttpServletRequest request){
        ErrorResponse response =
        new ErrorResponse(
            404,
            ex.getMessage(),
            request.getRequestURI(),
            System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(FeatureDisabledException.class)
    public ResponseEntity<Map<String,String>> handleFeatureDisabled(FeatureDisabledException ex){
        Map<String,String> body = new LinkedHashMap<>();
        body.put("HATA" , "ÖZELLİK DEVRE DIŞI");
        body.put("SEBEP:" , ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}
