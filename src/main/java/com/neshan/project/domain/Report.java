package com.neshan.project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.neshan.project.dto.ReportDTO;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.ReportType;

import com.neshan.project.myEnum.Side;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Indexed;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "report_type",
        discriminatorType = DiscriminatorType.STRING)
@Table(name = "reports", indexes = {
        @Index(name = "fn_index", columnList = "point")
})
public abstract class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "point")
    private Point point;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "status")
    private ReportStatus status;

    @Column(name = "rating")
    private int rating;

    @Column(name = "type")
    private ReportType type;


    public Report() {
        this.creationTime = LocalDateTime.now();
    }


    public int getWeight(){
        int weight;
        switch (type){
            case BUMP, CAMERA -> weight = 10;
            case ACCIDENT, TRAFFIC -> weight = 2;
            case POLICE -> weight = 5;
            default -> weight = 1;
        }
        return weight;
    }
}

