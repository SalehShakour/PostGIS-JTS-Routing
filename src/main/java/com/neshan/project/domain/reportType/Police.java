package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.*;
import com.neshan.project.myEnum.Side;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("police")
@Getter
@Setter
public class Police extends Report {
    private String policeSide;

    public Police(User user, Geometry geom, Side policeSide) {
        super(user, geom, ReportStatus.PENDING, ReportType.POLICE);
        this.policeSide = policeSide.name();
    }
}
