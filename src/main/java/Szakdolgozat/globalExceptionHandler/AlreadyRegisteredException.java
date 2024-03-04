package Szakdolgozat.globalExceptionHandler;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;

public class AlreadyRegisteredException extends ValidationException {
    public AlreadyRegisteredException() {
    }

    public AlreadyRegisteredException(String message) {
        super(message);
    }

    public AlreadyRegisteredException(String message, HttpStatus httpStatus) {
        super(message);
    }
}