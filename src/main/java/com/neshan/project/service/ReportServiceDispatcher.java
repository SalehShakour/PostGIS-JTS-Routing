package com.neshan.project.service;

import com.neshan.project.domain.reportType.*;
import com.neshan.project.myEnum.ReportType;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static java.util.function.UnaryOperator.identity;


@Service
@Getter
public class ReportServiceDispatcher {
    private final ReportService<Accident> accidentService;
    private final ReportService<Bump> bumpService;
    private final ReportService<Camera> cameraService;
    private final ReportService<Police> policeService;
    private final ReportService<Traffic> trafficService;

    private final Map<ReportType, ReportService<?>> reportServiceMap;

    @Autowired
    public ReportServiceDispatcher(
            ReportService<Accident> accidentService,
            ReportService<Bump> bumpService,
            ReportService<Camera> cameraService,
            ReportService<Police> policeService,
            ReportService<Traffic> trafficService) {
        this.accidentService = accidentService;
        this.bumpService = bumpService;
        this.cameraService = cameraService;
        this.policeService = policeService;
        this.trafficService = trafficService;

        this.reportServiceMap = Map.ofEntries(
                entry(ReportType.ACCIDENT, accidentService),
                entry(ReportType.BUMP, bumpService),
                entry(ReportType.CAMERA, cameraService),
                entry(ReportType.POLICE, policeService),
                entry(ReportType.TRAFFIC, trafficService)
        );
    }

}

