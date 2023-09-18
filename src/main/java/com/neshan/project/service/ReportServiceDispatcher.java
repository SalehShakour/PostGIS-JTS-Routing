package com.neshan.project.service;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.reportType.*;
import com.neshan.project.dto.ReportDTO;
import com.neshan.project.dto.mapper.ReportMapper;
import com.neshan.project.myEnum.ReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@Getter
@AllArgsConstructor
public class ReportServiceDispatcher {
    private final ReportMapper mapper;
    private final ReportService<Accident> accidentService;
    private final ReportService<Bump> bumpService;
    private final ReportService<Camera> cameraService;
    private final ReportService<Police> policeService;
    private final ReportService<Traffic> trafficService;



    public void dispatchAndSave(ReportDTO reportDTO){
        switch (reportDTO.getReportType()){
            case TRAFFIC -> trafficService.save(mapper.reportDTOToTraffic(reportDTO));
            case ACCIDENT -> accidentService.save(mapper.reportDTOToAccident(reportDTO));
            case CAMERA -> cameraService.save(mapper.reportDTOToCamera(reportDTO));
            case BUMP -> bumpService.save(mapper.reportDTOToBump(reportDTO));
            case POLICE -> policeService.save(mapper.reportDTOToPolice(reportDTO));
        }
    }
}


