package com.loginform.loginform.controller;

import ch.qos.logback.core.model.Model;
import ch.qos.logback.core.model.Model;
import com.loginform.loginform.model.UsersModel;
import com.loginform.loginform.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addText("registerRequest");
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addText("loginRequest");
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request : " + usersModel);
        UsersModel registeredUser= usersService.registerUser(usersModel.getLogin(),usersModel.getPassword(),usersModel.getEmail());
        return registeredUser==null?"error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String register(@ModelAttribute UsersModel usersModel,Model model){
        System.out.println("login request : " + usersModel);
        UsersModel authenticated= usersService.authenticate(usersModel.getLogin(),usersModel.getPassword());
        if(authenticated!=null){
            model.addText("userLogin");
            return "personal_page";
        }else{
            return "error_page";
        }
    }
}
