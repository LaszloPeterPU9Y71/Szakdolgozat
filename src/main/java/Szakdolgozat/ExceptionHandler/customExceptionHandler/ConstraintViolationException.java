package Szakdolgozat.ExceptionHandler.customExceptionHandler;

import org.springframework.http.HttpStatus;

public class ConstraintViolationException extends RuntimeException{

    public ConstraintViolationException(){
        super();
    }

    public ConstraintViolationException(String message){
        super(message);
    }

    public ConstraintViolationException(HttpStatus httpStatus, String message){
        super();
    }
}
