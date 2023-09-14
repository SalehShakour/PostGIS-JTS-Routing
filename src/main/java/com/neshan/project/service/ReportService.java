package com.neshan.project.service;

// Import the necessary packages

import com.neshan.project.domain.Report;
import com.neshan.project.exception.CustomException;
import com.neshan.project.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService<T extends Report> {

    private ReportRepository<T> repository;

    public T reportValidation(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException("Report not found"));
    }

    @Transactional
    public T save(T report) {
        return repository.save(report);
    }

    @Transactional(readOnly = true)
    public T findById(Long id) {
        return reportValidation(id);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.delete(reportValidation(id));
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }
}

