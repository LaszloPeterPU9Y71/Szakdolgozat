package Szakdolgozat.ExceptionHandler.customExceptionHandler;

import org.springframework.http.HttpStatus;

public class DataAlreadyExistsException extends RuntimeException {

    public DataAlreadyExistsException(){
        super();
    }
    public DataAlreadyExistsException(String message){
        super(message);
    }

    public DataAlreadyExistsException(HttpStatus httpStatus, String message) {
        super(message);
    }
}
