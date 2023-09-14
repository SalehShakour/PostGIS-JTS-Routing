package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.*;
import jakarta.persistence.*;

import lombok.*;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

@Entity
@DiscriminatorValue("police")
@Getter
@Setter
@NoArgsConstructor
public class Police extends Report {
    private Side policeSide;

    public Police(User user, Point point, Side policeSide) {
        super(user, point, ReportStatus.PENDING, ReportType.POLICE);
        this.policeSide = policeSide;
    }
}
