package Szakdolgozat.ExceptionHandler.customExceptionHandler;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)

public class AlreadyCreatedException extends ValidationException {

    public AlreadyCreatedException(){
    }
    public AlreadyCreatedException(String message){
        super(message);
    }

    public AlreadyCreatedException(String message, HttpStatus httpStatus){
        super(message);
    }
}
