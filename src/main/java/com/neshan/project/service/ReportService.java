package com.neshan.project.service;

// Import the necessary packages

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.domain.reportType.Accident;
import com.neshan.project.domain.reportType.Bump;
import com.neshan.project.dto.AccidentDTO;
import com.neshan.project.dto.BumpDTO;
import com.neshan.project.dto.PointDTO;
import com.neshan.project.exception.CustomException;
import com.neshan.project.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService<T extends Report> {

    private final ReportRepository<T> repository;
    private final WKTReader wktReader;

    public T reportValidation(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException("Report not found"));
    }

    @Transactional
    public void save(T report) {
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

    public Bump createBump(User currentUser, BumpDTO bumpDTO) {
        Point point = createPoint(bumpDTO.pointDTO());
        return new Bump(currentUser,point);
    }
    public Accident createAccident(User currentUser, AccidentDTO accidentDTO){
        Point point = createPoint(accidentDTO.pointDTO());
        return new Accident(currentUser,point,accidentDTO.severity());
    }




    public Point createPoint(PointDTO pointDTO){
        Point point;
        System.out.println(pointDTO.getX());
        String wellKnownText = String.format("POINT(%.6f %.6f)", pointDTO.getX(),pointDTO.getY());
        try {
            point = (Point) wktReader.read(wellKnownText);
        } catch (ParseException e) {
            throw new CustomException(e.getMessage());
        }
        return point;
    }
}

