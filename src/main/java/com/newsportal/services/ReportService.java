package com.newsportal.services;

import com.newsportal.models.Report;

public interface ReportService {
    Report findById(Long reportId);

    void update(Report report);

    void delete(Report report);
}
