package com.newsportal.controllers;

import com.newsportal.models.User;
import com.newsportal.services.CommentService;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class CommentsController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/create/comment")
    public RedirectView create(@RequestParam("articleId") String articleId,
                                      @RequestParam("commentText") String commentText,
                                      Principal principal) {
        User author = userService.findByUsername(principal.getName());
        commentService.create(Long.valueOf(articleId), author, commentText);
        return new RedirectView("/article?articleId=" + articleId);
    }
}
