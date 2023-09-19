package com.neshan.project.service;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.reportType.*;
import com.neshan.project.dto.ReportResponseDTO;
import com.neshan.project.dto.mapper.ReportMapper;
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
    private final ReportMapper mapper;


    public LineString toLinestring(String lineStringWkt) {
        try {
            LineString lineString = (LineString) wktReader.read(lineStringWkt);
            lineString.setSRID(4326);
            return lineString;
        } catch (ParseException e) {
            throw new CustomException(e.getMessage());
        }
    }
    public List<Report> getAllReports(LineString lineString){
        return reportRepo.findReportsWithinDistance(lineString, 10);
    }


    public List<ReportResponseDTO> routeAnalysis(List<Report> reports) {
        return mapper.toReportResponseDTOList(reports);
    }


}
