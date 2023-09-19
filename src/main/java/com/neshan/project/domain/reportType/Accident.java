package com.neshan.project.domain.reportType;


import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.dto.ReportDTO;
import com.neshan.project.myEnum.*;
import jakarta.persistence.*;

import lombok.*;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

@Entity
@DiscriminatorValue("ACCIDENT")
@Getter
@Setter
public class Accident extends Report {
    private AccidentSeverity accidentSeverity;

    public Accident() {
        this.setRating(5);
        this.setStatus(ReportStatus.OPEN);
    }
}
