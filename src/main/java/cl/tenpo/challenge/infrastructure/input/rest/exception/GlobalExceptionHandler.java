package cl.tenpo.challenge.infrastructure.input.rest.exception;

import cl.tenpo.challenge.application.exception.PercentageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PercentageNotFoundException.class})
    public ResponseEntity<Object> handle(PercentageNotFoundException exception) {
        Map<String, String> body = new HashMap<>();
        body.put("No existe un valor de porcentaje disponible para realizar conversion", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body);
    }

}
