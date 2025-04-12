package com.example.job_application.Job.Finder.Application.exception;

public class ResourceNotFoundException  extends  RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
