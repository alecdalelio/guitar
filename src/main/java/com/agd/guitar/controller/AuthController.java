package com.agd.guitar.controller;

import javax.validation.Valid;

import com.agd.guitar.model.User;
import com.agd.guitar.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value="/login")
    public String login(){
        return "login";
    }

    @GetMapping(value="/signup")
    public String regisration(Model model){
        User user = new User();
        model.addAttribute("user", user);
            return "registration";
    }

    @PostMapping(value="/signup")
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        User userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "Username is already taken");
        }
        if (!bindingResult.hasErrors()) {
            userService.saveNewUser(user);
            model.addAttribute("success", "Sign up successful!");
            model.addAttribute("user", new User());
        }
        return "registration";
    }    
}