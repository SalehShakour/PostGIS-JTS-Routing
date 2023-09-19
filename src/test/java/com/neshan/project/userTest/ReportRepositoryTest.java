package com.neshan.project.userTest;


import com.neshan.project.converter.PointConverter;
import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.dto.PointDTO;
import com.neshan.project.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Point;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReportRepositoryTest {

    @Autowired
    private ReportRepository<Report> reportRepository;

    @Test
    public void testFindReportsWithinDistance() {
        Point testGeometry = PointConverter.convert(new PointDTO(51,52));
        double distance = 100.0;
        List<Report> reports = reportRepository.findReportsWithinDistance(testGeometry, distance);
        assertNotNull(reports);
    }

    @Test
    public void testFindByPoint() {
        Point testPoint = PointConverter.convert(new PointDTO(51,52));
        List<Report> reports = reportRepository.findByPoint(testPoint);
        assertNotNull(reports);
    }

    @Test
    public void testGetAllPendingReports() {
        List<Report> reports = reportRepository.getAllPendingReports();
        assertNotNull(reports);

    }
}

