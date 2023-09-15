package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.*;
import jakarta.persistence.*;

import lombok.*;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

@Entity
@DiscriminatorValue("TRAFFIC")
@Getter
@Setter
@NoArgsConstructor
public class Traffic extends Report {

    private TrafficType trafficType;

    public Traffic(User user, Point point, TrafficType type, double degree, Side side) {
        super(user, point, ReportStatus.PENDING, degree, side);
        this.trafficType = type;
    }
}
