package com.example.todoapp.advices;

import com.example.todoapp.task.TaskIdParseException;
import com.example.todoapp.task.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(TaskIdParseException.class)
    public ResponseEntity<ErrorDetails> taskIdParseExceptionHandler(TaskIdParseException e){
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDetails);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDetails> taskNotFoundExceptionHandler(TaskNotFoundException e){
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

}
