package Szakdolgozat.globalExceptionHandler;

import org.springframework.http.HttpStatus;

public class MissingDataException extends RuntimeException{

    public MissingDataException(){

    }

    public MissingDataException(String message, HttpStatus httpStatus){
        super(message);
    }
}
