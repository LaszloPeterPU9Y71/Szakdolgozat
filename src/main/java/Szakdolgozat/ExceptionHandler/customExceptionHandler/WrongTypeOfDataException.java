package Szakdolgozat.ExceptionHandler.customExceptionHandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class WrongTypeOfDataException extends DataIntegrityViolationException {
    public WrongTypeOfDataException(String message){
        super(message);
    }
    public WrongTypeOfDataException(String message, HttpStatus httpStatus){
        super(message);
    }
}
