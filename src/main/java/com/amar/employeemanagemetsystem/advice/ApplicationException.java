package com.amar.employeemanagemetsystem.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //since this appli. is restful service so this annotation is applied otherwise @ControllerAdvice.@RestControllerAdvice is combination of @ControllerAdvice+@ResponseBody so we dont need to annotate the method with @ResponseBody
public class ApplicationException{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)//whenever this exception occur then this annotation tells to springboot to redirect to this method
    public Map<String,String> handleInavalidArgument(MethodArgumentNotValidException ex){
       //logic for better readable
         Map<String,String> errorMap=new HashMap<>();
         //getting all errors and converted to list by this method getFieldErrors()
         ex.getBindingResult().getFieldErrors().stream().forEach(error->{
             errorMap.put(error.getField(),error.getDefaultMessage());//error.getField() return issue
         });
        System.out.println(ex.getLocalizedMessage());
         return errorMap;
     }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)//whenever this exception occur then this annotation tells to springboot to redirect to this method
    public Map<String,String> handleInavalidArgument(java.sql.SQLIntegrityConstraintViolationException ex){
        //logic for better readable
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("error","SQLIntegrityConstraintViolation");//error.getField() return issue
        System.out.println(ex.getLocalizedMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(java.util.NoSuchElementException.class)//whenever this exception occur then this annotation tells to springboot to redirect to this method
    public Map<String,String> handleInavalidArgument(java.util.NoSuchElementException ex){
        //logic for better readable
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage","id not present");//error.getField() return issue
        System.out.println(ex.getLocalizedMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(CannotAccessId.class)//whenever this exception occur then this annotation tells to springboot to redirect to this method
    public Map<String,String> handleInavalidArgument(CannotAccessId ex){
        //logic for better readable
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage","Cannot see other employee Details");//error.getField() return issue
        System.out.println(ex.getLocalizedMessage());
        return errorMap;
    }

}
