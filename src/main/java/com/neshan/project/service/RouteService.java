package com.neshan.project.service;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.reportType.*;
import com.neshan.project.dto.PointDTO;
import com.neshan.project.dto.ReportDTO;
import com.neshan.project.exception.CustomException;
import com.neshan.project.myEnum.ReportType;
import com.neshan.project.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.springframework.stereotype.Service;

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

    public List<ReportDTO> routeAnalysis(LineString lineString) {
        List<ReportDTO> pointsWithinDistance = new ArrayList<>();
        List<Report> reports = reportRepo.findReportsWithinDistance(lineString, 10);

        for (Report report : reports) {
            Map<String, Object> additionalInfo = new HashMap<>(); // Create a map for additional information

            switch (report.getType()) {
                case ACCIDENT -> additionalInfo.put("severity", ((Accident) report).getAccidentSeverity().name());
                case TRAFFIC -> additionalInfo.put("trafficType", ((Traffic) report).getTrafficType().name());
                default -> {
                }
            }

            pointsWithinDistance.add(new ReportDTO(
                    report.getType(),
                    report.getPoint().toString(),
                    additionalInfo
            ));
        }

        return pointsWithinDistance;
    }

}
