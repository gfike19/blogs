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
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LogInController {
    @Autowired
    UserDao userDao;

    @GetMapping
    public String get(Model model, HttpSession session){
        model.addAttribute("title", "Log In");
        try {
            String msg = (String) session.getAttribute("msg");
            model.addAttribute("msg", msg);
        } catch (Exception e){}
        return "login";
    }

    @PostMapping
    public String post(HttpServletRequest request, Model model, HttpSession session) {
            boolean loggedIn = false;
            // check for logged in user trying to log in
        try {
            if (session.getAttribute("userId") != null) {
                model.addAttribute("msg", "You are already logged in!");
                return "redirect:/newBlog";
            }
        } catch (Exception e) {
        }
        // check for empty fields
        try {
            String username = request.getParameter("uname");
            String password = request.getParameter("pwd");
            // check that username is in database
            try {
                User u = userDao.findByName(username);
                // password validation
                if(!u.isMatchingPassword(password)) {
                    session.setAttribute("msg", "Invalid password");
                    return "redirect:/login";
                } else {
                    return "redirect:/newBlog";
                }
            } catch(Exception e) {
                session.setAttribute("msg", "That username does not exist");
                return "redirect:/signup";
            }

        } catch (Exception e) {
            session.setAttribute("msg", "One or more fields are empty");
            return "redirect:/login";
        }
    }
}
