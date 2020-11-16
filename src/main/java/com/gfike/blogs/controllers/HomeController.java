package com.gfike.blogs.controllers;

import com.gfike.blogs.daos.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    public BlogDao blogDao;

    @GetMapping
    public String index(Model model){
        model.addAttribute("allBlogs", blogDao.findAll());
        model.addAttribute("title","Home");
        return "index";
    }

    @GetMapping("error")
    public String err(Model model) {
        model.addAttribute("title", "Error");
        return "error";
    }
}
