package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.ReportType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("bump")
@NoArgsConstructor
@Getter
@Setter
public class Bump extends Report {
    public Bump(User user, Geometry geom){
        super(user,geom, ReportStatus.PENDING, ReportType.BUMP);
    }
}
