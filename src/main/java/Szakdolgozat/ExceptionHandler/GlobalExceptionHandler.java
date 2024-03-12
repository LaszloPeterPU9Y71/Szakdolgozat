package Szakdolgozat.ExceptionHandler;


import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> alreadyCreatedException(ValidationException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);

    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> alreadyRegisteredException(ResponseStatusException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> wrongTypeOfData (DataIntegrityViolationException exeption){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exeption.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> missingDataException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    @ExceptionHandler(Error.class)
    public ResponseEntity<Object> handleError(Error error){
        return ResponseEntity
                .badRequest()
                .body("Nem várt hiba történt, kérem frissítse az oldalt!");
    }

}
