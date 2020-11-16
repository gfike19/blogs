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
@RequestMapping("signup")
public class SignUpController {
    @Autowired
    UserDao userDao;

    @GetMapping
    public String get(Model model, HttpSession session) {
        try {
            String msg = (String) session.getAttribute("msg");
            model.addAttribute("msg", msg);
        } catch (Exception e) {
        }
        model.addAttribute("title", "Sign Up");
        return "signup";
    }

    @PostMapping
    public String post(HttpServletRequest request, Model model, HttpSession session) {
        String msg = "";

        try {
            if (session.getAttribute("userId") != null) {
                session.setAttribute("msg", "You are already logged in!");
                return "redirect:/newBlog";
            }
        } catch (Exception e) {
        }

        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");
        String vpwd = request.getParameter("verify");

        if (uname.isBlank() || pwd.isBlank() || vpwd.isBlank()) {
            msg += "One or more fields are empty<br>";
        }

        if (!pwd.equals(vpwd)) {
            msg += "Passwords do not match<br>";
            return "redirect:";
        }

        if (msg != null) {
            model.addAttribute("msg", msg);
            return "redirect:";
        }

        User u = new User(uname, pwd);
        userDao.save(u);
        session.setAttribute("userId", u.getId());
        model.addAttribute("username", uname);
        return "redirect:/newBlog";
    }
}
