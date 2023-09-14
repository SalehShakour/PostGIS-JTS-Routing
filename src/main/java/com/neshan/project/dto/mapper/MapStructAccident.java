package com.neshan.project.dto.mapper;

import com.neshan.project.domain.reportType.Accident;
import com.neshan.project.dto.AccidentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapStructAccident {

    MapStructAccident INSTANCE = Mappers.getMapper(MapStructAccident.class);

    @Mapping(target = "geometry", expression = "java(entity.getGeometry().toString())")
    AccidentDTO toDTO(Accident entity);
}