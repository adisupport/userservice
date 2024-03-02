package com.scaler.userservice.controllerAdvices;

import com.scaler.userservice.execptions.InvalidRequestDTO;
import com.scaler.userservice.execptions.NoUserFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(InvalidRequestDTO.class)
    ResponseEntity<String> handleBadInput(InvalidRequestDTO invalidRequestDTO){
        return new ResponseEntity<>(invalidRequestDTO.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoUserFoundException.class)
    ResponseEntity<String> userNotFound(NoUserFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
