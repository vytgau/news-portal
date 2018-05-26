package com.newsportal.controllers;

import com.newsportal.models.Article;
import com.newsportal.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/search/articles")
    public String searchArticles(@RequestParam("searchText") String searchText,
                                 @RequestParam(name = "p", defaultValue = "0") int pageNumber,
                                 Model model) {
        Page<Article> page = articleService.searchArticles(pageNumber, searchText);
        model.addAttribute("page", page);
        model.addAttribute("searchText", searchText);
        return "search";
    }

}
