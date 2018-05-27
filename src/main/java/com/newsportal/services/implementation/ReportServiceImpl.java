package com.newsportal.services.implementation;

import com.newsportal.models.Report;
import com.newsportal.repositories.ReportRepository;
import com.newsportal.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report findById(Long reportId) {
        return reportRepository.findById(reportId).get();
    }

    @Override
    public void update(Report report) {
        reportRepository.save(report);
    }

    @Override
    public void delete(Report report) { reportRepository.delete(report); }
}
