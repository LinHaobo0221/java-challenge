package jp.co.axa.apidemo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

    private Map<String, Object> getBody(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return body;
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex) {
        logger.error(ex.getMessage());

        return new ResponseEntity<>(getBody(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordExistException.class)
    public ResponseEntity<Object> handleRecordExistException(RecordExistException ex) {
        logger.error(ex.getMessage());

        return new ResponseEntity<>(getBody(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> paramExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> body = new LinkedHashMap<>();

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if (!allErrors.isEmpty()) {
                FieldError error = (FieldError) allErrors.get(0);
                body.put("message", error.getDefaultMessage());
                return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
            }
        } else {
            body.put("message", e.getMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
