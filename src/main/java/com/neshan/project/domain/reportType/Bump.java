package com.neshan.project.domain.reportType;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.ReportType;
import jakarta.persistence.*;

import lombok.*;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

@Entity
@DiscriminatorValue("bump")
@Getter
@Setter
@NoArgsConstructor
public class Bump extends Report {
    public Bump(User user, Point point){
        super(user,point, ReportStatus.PENDING, ReportType.BUMP);
    }
}
