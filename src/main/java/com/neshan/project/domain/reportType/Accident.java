package com.neshan.project.domain.reportType;


import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.AccidentSeverity;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.ReportType;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("accident")
@Getter
@Setter
public class Accident extends Report {

    private String severity;

    public Accident(User user, Geometry geom, AccidentSeverity severity) {
        super(user, geom, ReportStatus.PENDING, ReportType.ACCIDENT);
        this.severity = severity.name();
    }

}
