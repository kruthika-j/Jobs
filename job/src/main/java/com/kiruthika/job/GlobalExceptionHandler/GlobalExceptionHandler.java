// GlobalExceptionHandler.java
package com.kiruthika.job.GlobalExceptionHandler;

// import com.kiruthika.job.Exception.EmptyListException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleEmptyListException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empty list.Enter Id"+ ex);
    }
}
