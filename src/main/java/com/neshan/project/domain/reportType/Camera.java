package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.ReportType;
import com.neshan.project.myEnum.Side;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("camera")
@NoArgsConstructor
@Getter
@Setter
public class Camera extends Report {
    private String cameraSide;

    public Camera(User user, Geometry geom, Side side) {
        super(user, geom, ReportStatus.PENDING, ReportType.CAMERA);
        this.cameraSide = side.name();
    }
}
