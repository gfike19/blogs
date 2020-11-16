package com.gfike.blogs.controllers;

import com.gfike.blogs.daos.UserDao;
import com.gfike.blogs.dtos.RegisterFormDTO;
import com.gfike.blogs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("signup")
public class SignUpController {
    @Autowired
    UserDao userDao;

    @GetMapping
    public String get(@ModelAttribute RegisterFormDTO registerFormDTO,
                      Errors errors, HttpServletRequest request,
                      Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingUser = userDao.findByName(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userDao.save(newUser);
//        setUserInSession(request.getSession(), newUser);

        return "redirect:";
    }
}
