package com.neshan.project.domain;

import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.ReportType;

import com.neshan.project.myEnum.Side;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "report_type",
        discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "point")
    private Point point;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "status")
    private ReportStatus status;

    @Column(name = "rating")
    private int rating;

    @Column(name = "type")
    private ReportType type;

    public Report(
            User user, Point point, ReportType reportType, ReportStatus status
    ) {
        this.user = user;
        point.setSRID(4326);
        this.point = point;
        this.creationTime = LocalDateTime.now();
        this.status = status;
        this.type = reportType;
    }
}

