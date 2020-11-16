package com.gfike.blogs.controllers;

import com.gfike.blogs.daos.UserDao;
import com.gfike.blogs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("login")
public class LogInController {
    @Autowired
    UserDao userDao;

    @GetMapping
    public String get(Model model){
        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping
    public String post(HttpServletRequest request, Model model) {
        String msg = "";
        String username = request.getParameter("username");
        User u = new User();
        try {
            u = userDao.findByName(username);
        } catch(Exception e) {
            msg += "No username found. Please create an account<br>";
            model.addAttribute("msg", msg);
            return "redirect:/login";
        }
        u = userDao.findByName(username);
        String pwd = request.getParameter("pwd");
        if(!u.isMatchingPassword(pwd)) {
            msg += "Invalid password<br>";
            return "redirect:/login";
        }
        return "newBlog";
    }
}
