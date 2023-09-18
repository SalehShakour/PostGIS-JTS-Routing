package com.neshan.project.repository;


import com.neshan.project.domain.Report;
import com.neshan.project.domain.reportType.Accident;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ReportRepository<T extends Report> extends JpaRepository<T, Long> {

    @Query("SELECT r FROM Report r WHERE r.status = 1" +
            " AND ST_DWithin(ST_Transform(r.point,3857),ST_Transform(:lineString,3857), :distance) = true")
    List<T> findReportsWithinDistance(
            @Param("lineString") Geometry lineString,
            @Param("distance") double distance
    );

    List<Report> findByPoint(Point point);


}

