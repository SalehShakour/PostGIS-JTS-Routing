package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.ReportType;
import com.neshan.project.myEnum.Side;
import jakarta.persistence.*;

import lombok.*;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

@Entity
@DiscriminatorValue("BUMP")
@Getter
@Setter
@NoArgsConstructor
public class Bump extends Report {

    public Bump(User user, Point point, Side side) {
        super(user, point, ReportType.BUMP, ReportStatus.PENDING, side);
    }
}
