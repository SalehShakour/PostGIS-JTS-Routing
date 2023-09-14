package com.neshan.project.service;

// Import the necessary packages

import com.neshan.project.domain.Report;
import com.neshan.project.dto.PointDTO;
import com.neshan.project.exception.CustomException;
import com.neshan.project.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService<T extends Report> {

    private final ReportRepository<T> repository;

    public T reportValidation(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException("Report not found"));
    }
    public Point convertToGeom(PointDTO pointDTO){
        System.out.println("is ok !!!!!!!!!!!!!!!!!!");
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(pointDTO.getX(), pointDTO.getY()));
        System.out.println(point.toString());
        return point;
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

