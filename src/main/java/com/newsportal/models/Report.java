package com.newsportal.models;

import com.newsportal.models.enums.ReportState;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.ORDINAL)
    private ReportState state;

    @ManyToOne
    @JoinColumn(name = "fk_Articlesid")
    private Article article;

    private String comment;

    public Report() {}

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ReportState getState() {
        return state;
    }

    public void setState(ReportState state) {
        this.state = state;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
