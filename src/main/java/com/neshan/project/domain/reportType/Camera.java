package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.*;
import jakarta.persistence.*;

import lombok.*;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

@Entity
@DiscriminatorValue("camera")
@Getter
@Setter
@NoArgsConstructor
public class Camera extends Report {
    private Side cameraSide;

    public Camera(User user, Point point, Side side) {
        super(user, point, ReportStatus.PENDING, ReportType.CAMERA);
        this.cameraSide = side;
    }
}
