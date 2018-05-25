package com.newsportal.controllers;

import com.newsportal.models.User;
import com.newsportal.services.ArticleService;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;

@Controller
public class ArticleController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    /**
     * Opens homepage
     */
    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @GetMapping("/create/article")
    public String openArticleCreationView() {
        return "create-article";
    }

    @PostMapping("/create/article")
    public RedirectView createArticle(@RequestParam("articlePicture") MultipartFile articlePicture,
                                      @RequestParam("articleTitle") String articleTitle,
                                      @RequestParam("publishTime") String publishTime,
                                      @RequestParam(value = "groups", required = false) String[] groups,
                                      @RequestParam("articleText") String articleText,
                                      Principal principal) {
        User author = userService.findByUsername(principal.getName());
        articleService.createArticle(articlePicture, articleTitle, publishTime, groups, articleText, author);
        return new RedirectView("/");
    }

    /**
     * Returns article picture
     */
    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@RequestParam String articleId) {
        byte[] imageContent = articleService.findById(Long.valueOf(articleId)).getPicture();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }
}
