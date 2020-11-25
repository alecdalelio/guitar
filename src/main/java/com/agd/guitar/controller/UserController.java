package com.agd.guitar.controller;

import java.util.List;

import com.agd.guitar.model.User;
import com.agd.guitar.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public String getUsers(Model model){

        List<User> users = userService.findAll();
        User loggedInUser = userService.getLoggedInUser();  
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/users/{username}")
    public String getUser(@PathVariable(value="username") String username, Model model) {	
        
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "user";
        }
    
}

