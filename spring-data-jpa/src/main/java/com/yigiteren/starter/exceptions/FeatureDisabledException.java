package com.yigiteren.starter.exceptions;

public class FeatureDisabledException extends RuntimeException{
    public FeatureDisabledException(String message){
        super("feature disabled: " + message);
    }
}
