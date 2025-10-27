package com.infy.webApp.utility;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.infy.webApp.exception.CustomerException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvice {

   
   @ExceptionHandler(CustomerException.class)
   public ResponseEntity<String> handleCustomerException(CustomerException ex) {
       return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }

  
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
       Map<String, String> errors = new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach(error ->
           errors.put(error.getField(), error.getDefaultMessage())
       );
       return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
   }

   
   @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleGeneralException(Exception ex) {
       return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
