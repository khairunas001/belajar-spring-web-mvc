package bang_anas.belajar_spring_web_mvc.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException exception) {

        return ResponseEntity.badRequest().body("Validation Error : " + exception.getMessage());

        // cara biasa
        //        return new ResponseEntity<>(
        //                "Validation Error : " + exception.getMessage(),
        //                HttpStatus.BAD_REQUEST
        //        );
    }

    public ResponseEntity<String> constraintViolationExceptionon(ConstraintViolationException exception) {

        return ResponseEntity.badRequest().body("Validation Error : " + exception.getMessage());

        // cara biasa
        //        return new ResponseEntity<>(
        //                "Validation Error : " + exception.getMessage(),
        //                HttpStatus.BAD_REQUEST
        //        );


    }


}
