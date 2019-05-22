package ssu.org.epam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ssu.org.epam.exception.CustomBuisnesException;
import ssu.org.epam.model.Exception;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler( CustomBuisnesException.class)
    public ResponseEntity<Exception> responseEntity (CustomBuisnesException e) {
        Exception exception = new Exception(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Exception> responseNotReadable (HttpMessageNotReadableException e) {
        String err_message = e.getMessage();
        err_message = err_message.substring(0, err_message.indexOf(":", err_message.indexOf(":") + 1));
        Exception exception = new Exception(String.format("Error in parse specified parameters! " +
                "More information: %s.", err_message), System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
