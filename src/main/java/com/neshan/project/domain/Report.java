package com.neshan.project.domain;

import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.ReportType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    public Report(User user, Geometry geom, ReportStatus status, ReportType type){
        this.user = user;
        this.geometry = geom;
        this.creationTime = LocalDateTime.now();
        this.status = status.name();
        this.type = type.name();
    }

}

