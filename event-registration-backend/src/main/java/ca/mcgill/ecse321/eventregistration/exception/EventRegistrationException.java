package ca.mcgill.ecse321.eventregistration.exception;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;

public class EventRegistrationException extends RuntimeException {
    private HttpStatus status;
    public EventRegistrationException(@NonNull HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
