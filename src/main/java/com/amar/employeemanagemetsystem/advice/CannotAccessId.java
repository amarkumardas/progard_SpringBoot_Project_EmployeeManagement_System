package com.amar.employeemanagemetsystem.advice;

public class CannotAccessId extends Exception{
    public CannotAccessId(){//non parameterized constructor
        super();
    }
    public CannotAccessId(String message){// parameterized constructor to pass message

        super(message);
    }
}
