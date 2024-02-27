package Szakdolgozat.ExceptionHandler;


import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleException(ValidationException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);

        /*return ResponseEntity
                .badRequest()
                .body(ex.getMessage());*/
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleException(ResponseStatusException ex){
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleException(){
        return ResponseEntity
                .badRequest()
                .body("Az adatok kitöltése hiányos");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
    @ExceptionHandler(Error.class)
    public ResponseEntity<?> handleError(Error error){
        return ResponseEntity
                .badRequest()
                .body("Nem várt hiba történt, kérem frissítse az oldalt!");
    }

}
