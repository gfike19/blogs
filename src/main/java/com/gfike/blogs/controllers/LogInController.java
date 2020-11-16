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
        String username = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        User u;
        if(username.isEmpty() || pwd.isEmpty()) {
            msg += "One or more fields are empty<br>";
        }

        if(!msg.isEmpty()) {
            model.addAttribute("msg", msg);
            return "redirect:";
        }

        try {
            u = userDao.findByName(username);
        } catch(Exception e) {
            msg += "No username found. Please create an account<br>";
            model.addAttribute("msg", msg);
        }
        u = userDao.findByName(username);

        if(!u.isMatchingPassword(pwd)) {
            msg += "Invalid password<br>";
        }

        if(!msg.isEmpty()) {
            model.addAttribute("msg", msg);
            return "redirect:/login";
        }

        return "redirect:/newBlog";
    }
}
