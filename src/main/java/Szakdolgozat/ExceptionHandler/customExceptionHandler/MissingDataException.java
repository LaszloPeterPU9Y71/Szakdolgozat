package Szakdolgozat.ExceptionHandler.customExceptionHandler;

import org.springframework.http.HttpStatus;

public class MissingDataException extends RuntimeException {

    public MissingDataException(){
    }

    public MissingDataException(String message){
        super(message);
    }
    public MissingDataException(String message, HttpStatus httpStatus){
        super(message);
    }
}
