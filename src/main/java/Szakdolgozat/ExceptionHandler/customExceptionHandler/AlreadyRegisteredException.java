package Szakdolgozat.ExceptionHandler.customExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException() {
        super();
    }

    public AlreadyRegisteredException(String message) {
        super(message);
    }

}