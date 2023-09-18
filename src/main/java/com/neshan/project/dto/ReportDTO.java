package com.neshan.project.dto;

import com.neshan.project.domain.User;
import com.neshan.project.myEnum.AccidentSeverity;
import com.neshan.project.myEnum.ReportType;
import com.neshan.project.myEnum.Side;
import com.neshan.project.myEnum.TrafficType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDTO {
    private User user;
    private ReportType reportType;
    public PointDTO pointDTO;
    private AccidentSeverity severity;
    private Side side;
    private TrafficType trafficType;

    @Override
    public String toString() {
        return "ReportDTO{" +
                "user=" + user.toString() +
                ", reportType=" + reportType +
                ", pointDTO=" + pointDTO.toString() +
                ", severity=" + severity +
                ", side=" + side +
                ", trafficType=" + trafficType +
                '}';
    }
}
