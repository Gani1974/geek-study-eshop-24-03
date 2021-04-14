package ru.geekbrains.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonAdvice {

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
