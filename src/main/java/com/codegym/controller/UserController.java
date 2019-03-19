package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String getHome(Model model){
        model.addAttribute("users", userService.findAll());
        return "home";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/create")
    public String postCreatePage(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "index";
        }
        else {
            userService.save(user);
            model.addAttribute("user", user);
            return "result";
        }
    }
}
