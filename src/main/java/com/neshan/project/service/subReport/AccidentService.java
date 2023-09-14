package com.neshan.project.service.subReport;

import com.neshan.project.domain.reportType.Accident;
import com.neshan.project.exception.CustomException;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.repository.subReport.AccidentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentRepository accidentRepo;

    public Accident reportValidation(Long id) {
        return accidentRepo.findById(id)
                .orElseThrow(() -> new CustomException("Accident report not found"));
    }

    @Transactional
    public void addAccidentReport(Accident accident) {
        accidentRepo.save(accident);
    }

    @Transactional(readOnly = true)
    public Accident getAccidentReportById(Long id) {
        return reportValidation(id);
    }

    @Transactional
    public Accident updateAccidentReportStatus(Long id, ReportStatus status) {
        Accident accident = reportValidation(id);
        accident.setStatus(status);
        return accidentRepo.save(accident);
    }
}
