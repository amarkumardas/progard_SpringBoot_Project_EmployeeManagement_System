package com.amar.employeemanagemetsystem.advice;

public class CannotAccessId extends Exception{
    public CannotAccessId(){
        super();
    }
    public CannotAccessId(String message){
        super(message);
    }
}
