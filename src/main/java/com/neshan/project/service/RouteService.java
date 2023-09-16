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
import java.util.List;

@Service
@AllArgsConstructor
public class RouteService {
    private final WKTReader wktReader;
//    private final ReportRepository<Report> reportRepo;
    private final ReportRepository<Accident> accidentRepo;
    private final ReportRepository<Traffic> trafficRepo;
    private final ReportRepository<Bump> bumpRepo;
    private final ReportRepository<Police> policeRepo;
    private final ReportRepository<Camera> cameraRepo;

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

        List<Accident> reports = accidentRepo.findReportsWithinDistance(lineString,50);
        for (Accident report: reports){
            pointsWithinDistance.add(new ReportDTO(
                    ReportType.ACCIDENT,report.getPoint().toString(),report.getAccidentSeverity().name())
            );
        }
        return pointsWithinDistance;
    }
}
