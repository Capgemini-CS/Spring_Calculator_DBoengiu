package com.capgemini.calculator.exception;

public class WrongOperatorException extends RuntimeException{
    public WrongOperatorException(String message) {
        super(message);
    }
}
