package Szakdolgozat.ExceptionHandler.customExceptionHandler;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(){
        super();
    }

    public DataNotFoundException(String message){
        super(message);
    }

    public DataNotFoundException(HttpStatus httpStatus, String message){
        super(message);
    }

}
