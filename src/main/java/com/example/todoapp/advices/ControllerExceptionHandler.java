package com.example.todoapp.advices;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

//@RestControllerAdvice
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String ERROR_VIEW = "error";

//    @ExceptionHandler(TaskIdParseException.class)
//    public ResponseEntity<ErrorDetails> taskIdParseExceptionHandler(TaskIdParseException e){
//        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDetails);
//    }
//
//    @ExceptionHandler(TaskNotFoundException.class)
//    public ResponseEntity<ErrorDetails> taskNotFoundExceptionHandler(TaskNotFoundException e){
//        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
//    }

//    @ExceptionHandler(TaskIdParseException.class)
//    public ModelAndView handleError(){
//        System.out.println("SOM TU");
//        //model.addAttribute("message", "Something bad happened");
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("message", "UJUJ");
//        mav.setViewName("error");
//        return mav;
//    }

    @ExceptionHandler(SQLException.class)
    public ModelAndView databaseExceptionHandler(SQLException e){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Sorry, it looks like we are not able to connect with database." +
                "\nPlease try it later.");
        mav.setViewName(ControllerExceptionHandler.ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView httpRequestMethodNotSupportedExceptionHandler(HttpServletRequest request){
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String message = "Sorry, no mapping found for the %s requested on %s".formatted(method, url);
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", message);
        mav.setViewName(ControllerExceptionHandler.ERROR_VIEW);
        return mav;
    }



}
