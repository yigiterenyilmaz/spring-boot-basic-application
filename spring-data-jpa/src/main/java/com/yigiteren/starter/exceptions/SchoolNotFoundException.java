package com.yigiteren.starter.exceptions;

public class SchoolNotFoundException extends RuntimeException{
    public SchoolNotFoundException(Integer id){
        super("school not found with id: " + id);
    }
}
