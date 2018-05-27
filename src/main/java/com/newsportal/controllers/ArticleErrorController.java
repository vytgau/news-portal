package com.newsportal.controllers;

import com.newsportal.models.Article;
import com.newsportal.models.Report;
import com.newsportal.models.enums.ReportState;
import com.newsportal.services.ArticleService;
import com.newsportal.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Set;

@Controller
public class ArticleErrorController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ReportService reportService;

    @GetMapping(value = "/article-reports")
    public String openArticleEditForm(@RequestParam(name = "articleId")Long articleId,
                                      Model model) {
        Article article = articleService.findById(articleId);
        Set<Report> reports = article.getReports();

        model.addAttribute("article", article);
        model.addAttribute("reports", reports);
        model.addAttribute("reportStates", ReportState.class);

        return "articleError";
    }

    @PostMapping("/mark-as-solved")
    public RedirectView markErrorAsSolved(@RequestParam("reportId") String reportId) {
        Report report = reportService.findById(Long.valueOf(reportId));
        report.setState(ReportState.Issprestas);
        reportService.update(report);

        return new RedirectView("/article-reports?articleId=" + report.getArticle().getId());
    }

    @PostMapping(value = "delete-report")
    public RedirectView delete(@RequestParam("reportId") Long reportId) {
        Report report = reportService.findById(reportId);
        Long articleId = report.getArticle().getId();

        reportService.delete(report);

        return new RedirectView("/article-reports?articleId=" + articleId);
    }
}
