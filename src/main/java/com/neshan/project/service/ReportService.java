package com.neshan.project.service;


import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.domain.reportType.*;
import com.neshan.project.dto.*;
import com.neshan.project.exception.CustomException;
import com.neshan.project.myEnum.Side;
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

    public void updateRating(Long id, int number){
        T report = repository.findById(id).orElseThrow(()->new CustomException("Report not found."));
        report.setRating(report.getRating()+number);
        repository.save(report);
    }

    public Bump createBumpObject(User currentUser,
                                 BumpDTO bumpDTO) {

        Point point = createPoint(bumpDTO.pointDTO());
        return new Bump(
                currentUser, point
        );
    }

    public Accident createAccidentObject(User currentUser,
                                         AccidentDTO accidentDTO) {

        Point point = createPoint(accidentDTO.pointDTO());
        return new Accident(
                currentUser, point, accidentDTO.severity()
        );
    }

    public Camera createCameraObject(User currentUser,
                                     CameraDTO cameraDTO) {

        Point point = createPoint(cameraDTO.pointDTO());
        return new Camera(
                currentUser, point
        );
    }

    public Police createPoliceObject(User currentUser,
                                     PoliceDTO policeDTO) {

        Point point = createPoint(policeDTO.pointDTO());
        return new Police(
                currentUser, point
        );
    }

    public Traffic createTrafficObject(User currentUser,
                                       TrafficDTO trafficDTO) {

        Point point = createPoint(trafficDTO.pointDTO());
        return new Traffic(
                currentUser, point, trafficDTO.trafficType()
        );
    }


    public Point createPoint(PointDTO pointDTO) {
        Point point;
        System.out.println(pointDTO.getX());
        String wellKnownText = String.format("POINT(%.6f %.6f)", pointDTO.getX(), pointDTO.getY());
        try {
            point = (Point) wktReader.read(wellKnownText);
        } catch (ParseException e) {
            throw new CustomException(e.getMessage());
        }
        return point;
    }


}

