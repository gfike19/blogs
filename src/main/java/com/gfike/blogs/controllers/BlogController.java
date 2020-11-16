package com.gfike.blogs.controllers;

import com.gfike.blogs.daos.BlogDao;
import com.gfike.blogs.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("newBlog")
public class BlogController {

    @Autowired
    BlogDao blogDao;

    @GetMapping
    public String get(Model model, HttpSession session) {
        try {
            if(session.getAttribute("userId") == null) {
                session.setAttribute("msg", "You must be logged in to write a new post");
                return "redirect:/login";
            }
        } catch (Exception e) {}

        model.addAttribute("title", "New Blog");
        model.addAttribute("blog", new Blog());
        return "newBlog";
    }

    @PostMapping
    public String post(Model model, @ModelAttribute @Validated Blog newBlog, Errors errors) {
        if(errors.hasErrors()) {
            return "redirect:/newBlog";
        }

        blogDao.save(newBlog);
        return "redirect:/";
    }
}
