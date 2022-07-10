package com.timor.advice;



import com.timor.domain.ValidError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


/**
 * @author CXH
 * @description
 * @create 2022-07-10 14:06
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidException(MethodArgumentNotValidException e){
        ModelAndView modelAndView = new ModelAndView("register");
        System.out.println("error");

        FieldError fieldError = e.getBindingResult().getFieldError();
        System.out.println(fieldError.getCode());
        System.out.println(fieldError.getField());
        System.out.println(fieldError.getDefaultMessage());
        modelAndView.addObject(fieldError.getField() + "Msg",fieldError.getDefaultMessage());
        return modelAndView;
    }

}
