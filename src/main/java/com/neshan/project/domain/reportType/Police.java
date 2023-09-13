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
@DiscriminatorValue("police")
@NoArgsConstructor
@Getter
@Setter
public class Police extends Report {
    private String policeSide;

    public Police(User user, Geometry geom, Side policeSide) {
        super(user, geom, ReportStatus.PENDING, ReportType.POLICE);
        this.policeSide = policeSide.name();
    }
}
