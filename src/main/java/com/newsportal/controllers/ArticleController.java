package com.newsportal.controllers;

import com.newsportal.models.*;
import com.newsportal.services.ArticleService;
import com.newsportal.services.GroupService;
import com.newsportal.services.GroupUserService;
import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupUserService groupUserService;

    /**
     * Opens homepage
     */
    @GetMapping(value = "/")
    public String home(@RequestParam(name = "p", defaultValue = "0") int pageNumber, Model model) {
        Page<Article> page = articleService.findArticlesHomePage(pageNumber);
        model.addAttribute("page", page);
        return "home";
    }

    /**
     * Opens article page
     */
    @GetMapping(value = "/article")
    public String openArticle(@RequestParam(name = "articleId") Long articleId,
                              @RequestParam(name = "groupId", required = false) Long groupId,
                              Principal principal,
                              Model model) {
        if (groupId != null) {
            String username = principal.getName();
            Group group = groupService.findById(Long.valueOf(groupId));
            User user = userService.findByUsername(username);
            GroupUser groupUser = groupUserService.findFirstByGroupIdAndUserId(group.getId(), user.getId());

            model.addAttribute("inGroup", true);
            model.addAttribute("group", group);
            model.addAttribute("groupUser", groupUser);
        }

        Article article = articleService.findById(articleId);
        List<Comment> comments = articleService.findArticleComments(articleId);

        model.addAttribute("article", article);
        model.addAttribute("comments", comments);

        return "article";
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

    @PostMapping("/article/report")
    @ResponseStatus(value = HttpStatus.OK)
    public void createReport(@RequestParam("articleId") String articleId,
                             @RequestParam("reportText") String reportText) {
        Article article = articleService.findById(Long.valueOf(articleId));
        articleService.createReport(article, reportText);
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

    @GetMapping(value = "/user-articles")
    public String openUserArticles(@RequestParam(name = "p", defaultValue = "0") int pageNumber,
                                   Principal principal,
                                   Model model) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Page<Article> page = articleService.findByAuthor(pageNumber, user);
        model.addAttribute("page", page);
        return "userArticleList";
    }

}
