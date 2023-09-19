package com.neshan.project;

import com.neshan.project.domain.Report;
import com.neshan.project.dto.mapper.ReportMapper;
import com.neshan.project.exception.CustomException;
import com.neshan.project.repository.ReportRepository;
import com.neshan.project.service.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {

    @Mock
    private ReportRepository<Report> reportRepo;
    @Mock
    private ReportMapper mapper;

    private RouteService routeService;
    private String lineStringWKT;
    private String invalidLineStringWKT;

    @BeforeEach
    void setUp() {
        WKTReader wktReader = new WKTReader();
        routeService = new RouteService(wktReader,reportRepo,mapper);
        lineStringWKT = "LINESTRING(51.4378 35.71678,51.43772 35.71681," +
                "51.43803 35.7174," +
                "51.43766 35.71753," +
                "51.43767 35.71755," +
                "51.43798 35.71821," +
                "51.43792 35.71823)";
        invalidLineStringWKT = "LINESTRING(51.4378 35.71678,51.43772 35.71681," +
                "51.43803 35.7174," +
                "51.43766 35.71753," +
                "51.43767 35.71755," +
                "51.43798 35.71821," +
                "51.43792";
    }
    @Test
    void toLinestringTest(){
        assertNotNull(routeService.toLinestring(lineStringWKT));
        assertEquals(routeService.toLinestring(lineStringWKT).getSRID(),4326);
        assertThrows(CustomException.class ,()->routeService.toLinestring(invalidLineStringWKT));
    }
}
