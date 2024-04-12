package Szakdolgozat.ExceptionHandler;


import Szakdolgozat.ExceptionHandler.customExceptionHandler.ConstraintViolationException;
import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataAlreadyExistsException;
import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<Object> dataAlreadyExistsException(DataAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> dataMissingException(){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
              .body("Az adatok rosszul, vagy hiányosan lettek kitöltve!");
    }
/*
    @ExceptionHandler(Error.class)
    public ResponseEntity<Object> handleError(){
        return ResponseEntity
                .badRequest()
                .body("Nem várt hiba történt, kérem frissítse az oldalt!");
    }*/

}
