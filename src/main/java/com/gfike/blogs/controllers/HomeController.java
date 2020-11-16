package com.gfike.blogs.controllers;

import com.gfike.blogs.daos.BlogDao;
import com.gfike.blogs.daos.UserDao;
import com.gfike.blogs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    BlogDao blogDao;

    @Autowired
    UserDao userDao;

    @GetMapping
    public String index(Model model, HttpSession session) {

        try {
            String msg = (String) session.getAttribute("msg");
            model.addAttribute("msg", msg);
        } catch (Exception e){}

        try {
            if (session.getAttribute("userId") != null) {
                int userId = Integer.parseInt((String) session.getAttribute("userId"));
                User u = userDao.findById(userId);
                model.addAttribute("username", u.getName());
            }
        } catch (Exception e) {
        }

        model.addAttribute("allBlogs", blogDao.findAll());
        model.addAttribute("title", "Home");
        return "index";
    }

    @GetMapping("error")
    public String err(Model model) {
        model.addAttribute("title", "Error");
        return "error";
    }

    @GetMapping("logout")
    public String logout(HttpSession session, Model model) {
        try {
            if (session.getAttribute("userId") == null) {
                session.setAttribute("msg", "You are not logged in!");
                return "redirect:/login";
            }
        } catch (Exception e){}

        session.invalidate();
        return "redirect:";
    }
}
