package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.signup_service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.authen_service.AuthenticationService;
import org.thymeleaf.util.StringUtils;

@Controller
public class LogInController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout) {
        model.addAttribute("invalidCredentials", error != null ? "invalid" : "");
        model.addAttribute("logoutFlag", logout != null ? "loggedOut" : "");
        model.addAttribute("signupStatus", signUpService.getMsgErr());
        model.addAttribute("account", new UserModel());
        return "login";
    }
}
