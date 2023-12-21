package com.ra.md05ss08bt.controller;

import com.ra.md05ss08bt.payload.request.LoginRequest;
import com.ra.md05ss08bt.payload.request.SignupRequest;
import com.ra.md05ss08bt.payload.response.UserInforResponse;
import com.ra.md05ss08bt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginRequest") LoginRequest user) {
        if (userService.login(user)) {
            return "redirect:/";
        }
        return "Đăng nhập thất bại";

    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("signupRequest", new SignupRequest());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("signupRequest") SignupRequest newUser) {
        UserInforResponse userInforResponse = userService.register(newUser);
        return "redirect:/auth/login";
    }

    @RequestMapping("/403")
    public String unauthorized() {
        return "403";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }
}
