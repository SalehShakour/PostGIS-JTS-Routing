package com.neshan.project.service;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.reportType.*;
import com.neshan.project.dto.ReportResponseDTO;
import com.neshan.project.exception.CustomException;
import com.neshan.project.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class RouteService {
    private final WKTReader wktReader;
    private final ReportRepository<Report> reportRepo;


    public LineString toLinestring(String lineStringWkt) {
        try {
            LineString lineString = (LineString) wktReader.read(lineStringWkt);
            lineString.setSRID(4326);
            return lineString;
        } catch (ParseException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<ReportResponseDTO> routeAnalysis(LineString lineString) {
        List<ReportResponseDTO> pointsWithinDistance = new ArrayList<>();
        List<Report> reports = reportRepo.findReportsWithinDistance(lineString, 10);
        for (Report report : reports) {
            if (report.getCreationTime().plusMinutes((long) report.getRating() * report.getWeight())
                    .isBefore(LocalDateTime.now())) {
                continue;
            }
            Map<String, Object> additionalInfo = new HashMap<>();

            switch (report.getType()) {
                case ACCIDENT -> additionalInfo.put("severity", ((Accident) report).getAccidentSeverity().name());
                case TRAFFIC -> additionalInfo.put("trafficType", ((Traffic) report).getTrafficType().name());
                default -> {
                }
            }

            pointsWithinDistance.add(new ReportResponseDTO(
                    report.getType(),
                    report.getPoint().toString(),
                    additionalInfo
            ));
        }

        return pointsWithinDistance;
    }

}
