package com.neshan.project.dto.mapper;

import com.neshan.project.domain.User;
import com.neshan.project.domain.reportType.Accident;
import com.neshan.project.dto.AccidentDTO;
import com.neshan.project.dto.PointDTO;
import org.geolatte.geom.codec.support.GeometryBuilder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTReader;
import org.mapstruct.*;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AccidentMapper {

}
