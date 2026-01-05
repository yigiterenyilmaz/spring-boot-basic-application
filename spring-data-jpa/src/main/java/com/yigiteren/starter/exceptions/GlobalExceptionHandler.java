package com.yigiteren.starter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yigiteren.starter.entities.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

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
}
