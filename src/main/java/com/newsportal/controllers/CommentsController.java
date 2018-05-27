package com.newsportal.controllers;

import com.newsportal.models.Comment;
import com.newsportal.models.User;
import com.newsportal.services.CommentService;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

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

    @PostMapping(value = "comment-delete")
    public RedirectView delete(@RequestParam("commentId") Long commentId) {
        Comment comment = commentService.findById(commentId);
        Long articleId = comment.getArticle().getId();

        commentService.delete(comment);

        return new RedirectView("/article?articleId=" + articleId);
    }

    @GetMapping(value = "comment-edit")
    public String openCommentEditForm(@RequestParam("commentId") Long commentId,
                                      Model model) {
        Comment comment = commentService.findById(commentId);

        model.addAttribute("comment", comment);

        return "commentEdit";
    }

    @PostMapping("/comment-edit")
    public RedirectView edit(@RequestParam("commentId") String commentId,
                                    @RequestParam("commentText") String commentText) {
        Comment comment = commentService.findById(Long.valueOf(commentId));

        comment.setText(commentText);
        comment.setEditDate(new Date(System.currentTimeMillis()));

        commentService.update(comment);

        return new RedirectView("/article?articleId=" + comment.getArticle().getId());
    }
}
