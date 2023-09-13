package com.neshan.project.domain.reportType;


import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.*;
import jakarta.persistence.*;

import lombok.*;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("accident")
@Getter
@Setter
@NoArgsConstructor
public class Accident extends Report {

    private String severity;

    public Accident(User user, Geometry geom, AccidentSeverity severity) {
        super(user, geom, ReportStatus.PENDING, ReportType.ACCIDENT);
        this.severity = severity.name();
    }

}
