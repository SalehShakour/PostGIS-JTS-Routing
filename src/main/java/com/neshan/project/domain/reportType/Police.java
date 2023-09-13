package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.*;
import jakarta.persistence.*;

import lombok.*;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("police")
@Getter
@Setter
@NoArgsConstructor
public class Police extends Report {
    private Side policeSide;

    public Police(User user, Geometry geom, Side policeSide) {
        super(user, geom, ReportStatus.PENDING, ReportType.POLICE);
        this.policeSide = policeSide;
    }
}
