package com.neshan.project.service.subReport;// Import the necessary packages

import com.neshan.project.domain.reportType.Traffic;
import com.neshan.project.exception.CustomException;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.repository.subReport.TrafficRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class TrafficService {

    private final TrafficRepository trafficRepo;

    public Traffic reportValidation(Long id) {
        return trafficRepo.findById(id)
                .orElseThrow(() -> new CustomException("Traffic report not found"));
    }


    @Transactional
    public void addTrafficReport(Traffic traffic) {
        trafficRepo.save(traffic);
    }

    @Transactional(readOnly = true)
    public Traffic getTrafficReportById(Long id) {
        return reportValidation(id);
    }

    @Transactional
    public Traffic updateTrafficReportStatus(Long id, ReportStatus status) {
        Traffic traffic = reportValidation(id);
        traffic.setStatus(status);
        return trafficRepo.save(traffic);
    }
}
