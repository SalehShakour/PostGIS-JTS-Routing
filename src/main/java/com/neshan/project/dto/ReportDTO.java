package com.neshan.project.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.ReportType;
import com.neshan.project.myEnum.Side;
import com.neshan.project.myEnum.TrafficType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ReportDTO {
    private User user;
    private ReportType reportType;
    public PointDTO pointDTO;
    private Map<String, String> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "user=" + user.toString() +
                ", reportType=" + reportType +
                ", pointDTO=" + pointDTO.toString() +
                '}';
    }
}
