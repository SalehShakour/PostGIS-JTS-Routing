package com.neshan.project.domain;

import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.ReportType;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Geometry;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="report_type",
        discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "geometry")
    private Geometry geometry;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "status")
    private ReportStatus status;

    @Column(name = "type")
    private ReportType type;

    @Column(name = "rating",columnDefinition = "integer default 5")
    private int rating;

    public Report(User user, Geometry geom, ReportStatus status, ReportType type){
        this.user = user;
        this.geometry = geom;
        this.creationTime = LocalDateTime.now();
        this.status = status;
        this.type = type;
    }

}

