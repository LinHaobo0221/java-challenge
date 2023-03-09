package jp.co.axa.apidemo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

    private Map<String, Object> getBody (RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return body;
    }

    @ExceptionHandler(RecordExistException.class)
    public ResponseEntity<Object> handleRecordExistException(RecordExistException ex, WebRequest request) {
        logger.error(ex.getMessage());

        return new ResponseEntity<>(getBody(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        logger.error(ex.getMessage());

        return new ResponseEntity<>(getBody(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidException(ConstraintViolationException ex) {
        List<String> collect = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(toList());
        Map<String, List<String>> body = new LinkedHashMap<>();
        body.put("message", collect);
        logger.error(collect.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
