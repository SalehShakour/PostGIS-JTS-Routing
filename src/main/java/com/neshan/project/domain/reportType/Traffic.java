package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.ReportType;
import com.neshan.project.myEnum.TrafficType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("traffic")
@NoArgsConstructor
@Getter
@Setter
public class Traffic extends Report {
    private String trafficType;

    public Traffic(User user, Geometry geom, TrafficType type) {
        super(user, geom, ReportStatus.PENDING, ReportType.TRAFFIC);
        this.trafficType = type.name();
    }
}
