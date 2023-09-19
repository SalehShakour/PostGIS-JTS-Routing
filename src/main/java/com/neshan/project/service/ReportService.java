package com.neshan.project.service;


import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.domain.reportType.*;
import com.neshan.project.dto.*;
import com.neshan.project.dto.mapper.ReportMapper;
import com.neshan.project.exception.CustomException;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportService<T extends Report> {

    private final ReportRepository<T> repository;
    private final WKTReader wktReader;
    private final ReportMapper mapper;

    public T reportValidation(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException("Report not found"));
    }

    @Transactional
    public void save(T report) {
        List<Report> sameCoordinate = repository.findByPoint(report.getPoint());

        for (Report r : sameCoordinate) {
            if (r.getType().equals(report.getType()) &&
                    Math.abs(ChronoUnit.MINUTES.between(r.getCreationTime(), report.getCreationTime())) <= 2) {
                throw new CustomException("A report with the same point, type and creationTime already exists");
            }
        }

        repository.save(report);
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

    public void updateRating(Long id, int number){
        T report = repository.findById(id).orElseThrow(()->new CustomException("Report not found."));
        report.setRating(report.getRating()+(number*report.getWeight()));
        repository.save(report);
    }


    public void updateStatus(Long id, ReportStatus newStatus) {
        T report = reportValidation(id);
        report.setStatus(newStatus);
        repository.save(report);
    }

    public List<ReportResponseDTO> getAllPendingReports() {
        List<Report> pendingReports = repository.getAllPendingReports();
        return mapper.toReportResponseDTOList(pendingReports);
    }
}

