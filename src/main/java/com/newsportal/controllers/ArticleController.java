package com.newsportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

    /**
     * Opens homepage
     */
    @GetMapping(value = "/")
    public String home() {
        return "home";
    }
}
