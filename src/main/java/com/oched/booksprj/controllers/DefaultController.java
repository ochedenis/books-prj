package com.oched.booksprj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @GetMapping("/hello/{name}")
    public ModelAndView getHello(@PathVariable String name, ModelAndView modelAndView) {
        modelAndView.addObject(name);
        modelAndView.setViewName("greeting");

        return modelAndView;
    }
}
