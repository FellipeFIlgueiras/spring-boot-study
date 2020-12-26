package br.com.filgueiras.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception.getStackTrace());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
