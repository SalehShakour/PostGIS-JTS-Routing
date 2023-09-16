package com.neshan.project.dto;

import com.neshan.project.myEnum.ReportType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReportDTO {

    private ReportType type;
    private String point;
    private Map<String, Object> additionalInformation;

    public ReportDTO() {
        this.additionalInformation = new HashMap<>();
    }
}

