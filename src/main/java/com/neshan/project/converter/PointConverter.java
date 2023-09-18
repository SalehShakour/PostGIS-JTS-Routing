package com.neshan.project.converter;

import com.neshan.project.dto.PointDTO;
import com.neshan.project.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

@Component
public class PointConverter {
    private static final WKTReader wktReader = new WKTReader();

    public static Point convert(PointDTO pointDTO) {
        Point point;
        String wellKnownText = String.format("POINT(%.6f %.6f)", pointDTO.getX(), pointDTO.getY());
        try {
            point = (Point) wktReader.read(wellKnownText);
            point.setSRID(4326);
        } catch (ParseException e) {
            throw new CustomException(e.getMessage());
        }
        return point;
    }
}
