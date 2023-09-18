package com.neshan.project.dto.mapper;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.reportType.*;
import com.neshan.project.dto.ReportDTO;
import com.neshan.project.converter.converter.PointConverter;
import com.neshan.project.dto.ReportResponseDTO;
import org.mapstruct.factory.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashMap;
import java.util.Map;


@Mapper(uses = PointConverter.class, componentModel = "spring")
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Mapping(target = "accidentSeverity", source = "severity")
    @Mapping(target = "type", source = "reportType")
    @Mapping(target = "point",
            expression = "java(com.neshan.project.converter.converter.PointConverter.convert(reportDTO.pointDTO))")
    Accident reportDTOToAccident(ReportDTO reportDTO);


    @Mapping(target = "trafficType", source = "trafficType")
    @Mapping(target = "type", source = "reportType")
    @Mapping(target = "point",
            expression = "java(com.neshan.project.converter.converter.PointConverter.convert(reportDTO.pointDTO))")
    Traffic reportDTOToTraffic(ReportDTO reportDTO);

    @Mapping(target = "type", source = "reportType")
    @Mapping(target = "point",
            expression = "java(com.neshan.project.converter.converter.PointConverter.convert(reportDTO.pointDTO))")
    Bump reportDTOToBump(ReportDTO reportDTO);

    @Mapping(target = "type", source = "reportType")
    @Mapping(target = "point",
            expression = "java(com.neshan.project.converter.converter.PointConverter.convert(reportDTO.pointDTO))")
    Camera reportDTOToCamera(ReportDTO reportDTO);

    @Mapping(target = "type", source = "reportType")
    @Mapping(target = "point",
            expression = "java(com.neshan.project.converter.converter.PointConverter.convert(reportDTO.pointDTO))")
    Police reportDTOToPolice(ReportDTO reportDTO);



    @Mapping(target = "additionalInformation", expression = "java(mapAdditionalInfo(report))")
    @Mapping(target = "point",
            expression = "java(report.point.toString())")
    ReportResponseDTO toReportResponseDTO(Report report);

    default Map<String, Object> mapAdditionalInfo(Report report) {
        Map<String, Object> additionalInfo = new HashMap<>();
        switch (report.getType()) {
            case ACCIDENT -> additionalInfo.put("severity", ((Accident) report).getAccidentSeverity().name());
            case TRAFFIC -> additionalInfo.put("trafficType", ((Traffic) report).getTrafficType().name());
            default -> {
            }
        }
        return additionalInfo;


    }
}


