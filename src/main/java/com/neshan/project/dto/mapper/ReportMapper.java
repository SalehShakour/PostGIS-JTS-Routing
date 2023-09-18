package com.neshan.project.dto.mapper;

import com.neshan.project.domain.reportType.Accident;
import com.neshan.project.dto.ReportDTO;
import com.neshan.project.converter.converter.PointConverter;
import org.mapstruct.factory.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = PointConverter.class)
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Mapping(target = "accidentSeverity", source = "severity")
    @Mapping(target = "type", source = "reportType")
    @Mapping(target = "point",
            expression = "java(com.neshan.project.converter.converter.PointConverter.convert(reportDTO.pointDTO))")
    Accident reportDTOToAccident(ReportDTO reportDTO);


}


