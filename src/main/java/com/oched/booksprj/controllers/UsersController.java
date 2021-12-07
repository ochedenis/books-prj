package com.oched.booksprj.controllers;

import com.oched.booksprj.entities.UserEntity;
import com.oched.booksprj.requests.NewUserRequest;
import com.oched.booksprj.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @GetMapping(value = "/add")
    public String getNewUserForm() {
        return "/users/addUser";
    }

    @PostMapping(value = "/add")
    public ModelAndView addNewUser(@ModelAttribute("request") NewUserRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("/users/newUser");
        modelAndView.addObject("user", this.userService.addNewUser(request));

        return modelAndView;
    }

    @GetMapping(value = "/all")
    public ModelAndView getUsersList(ModelAndView modelAndView) {
        modelAndView.addObject("list", this.userService.getUsersList());
        modelAndView.setViewName("/users/allUsers");

        return modelAndView;
    }
}
