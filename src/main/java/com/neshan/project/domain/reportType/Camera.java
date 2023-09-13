package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.*;
import jakarta.persistence.*;

import lombok.*;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("camera")
@Getter
@Setter
@NoArgsConstructor
public class Camera extends Report {
    private String cameraSide;

    public Camera(User user, Geometry geom, Side side) {
        super(user, geom, ReportStatus.PENDING, ReportType.CAMERA);
        this.cameraSide = side.name();
    }
}
