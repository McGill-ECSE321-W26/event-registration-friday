package ca.mcgill.ecse321.eventregistration.exception;

import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventRegistrationExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder errorMessage = new StringBuilder();
        for (var error : e.getConstraintViolations()) {
            errorMessage.append(error.getMessage()).append("; ");
        }
        return new ResponseEntity<>(new ErrorDto(errorMessage.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventRegistrationException.class)
    public ResponseEntity<ErrorDto> handleEventRegistrationException(EventRegistrationException e) {
        return new ResponseEntity<>(new ErrorDto(e.getMessage()), e.getStatus());
    }
}
