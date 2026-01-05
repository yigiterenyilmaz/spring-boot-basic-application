package com.yigiteren.starter.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Integer id){
        super("student not found with id: " + id);
    }
}
