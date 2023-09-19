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
@DiscriminatorValue("POLICE")
@Getter
@Setter

public class Police extends Report {
    public Police() {
        this.setRating(20);
        this.setStatus(ReportStatus.OPEN);
    }
}
