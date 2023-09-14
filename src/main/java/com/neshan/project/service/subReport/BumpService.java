package com.neshan.project.service.subReport;

import com.neshan.project.domain.reportType.Bump;
import com.neshan.project.exception.CustomException;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.repository.subReport.BumpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BumpService {

    private final BumpRepository bumpRepo;

    public Bump reportValidation(Long id) {
        return bumpRepo.findById(id)
                .orElseThrow(() -> new CustomException("Bump report not found"));
    }

    @Transactional
    public void addBumpReport(Bump bump) {
        bumpRepo.save(bump);
    }

    @Transactional(readOnly = true)
    public Bump getBumpReportById(Long id) {
        return reportValidation(id);
    }

    @Transactional
    public Bump updateBumpReportStatus(Long id, ReportStatus status) {
        Bump bump = reportValidation(id);
        bump.setStatus(status);
        return bumpRepo.save(bump);
    }
}

