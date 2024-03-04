package Szakdolgozat.globalExceptionHandler;

import org.springframework.http.HttpStatus;

public class AlreadyCreatedException extends Exception {


    public AlreadyCreatedException(String message){
        super(message);
    }

    public AlreadyCreatedException(String message, HttpStatus httpStatus){
        super(message);
    }
}
