package com.example.jobFinderApplication.exception;

public class ResourceNotFoundException  extends  RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
