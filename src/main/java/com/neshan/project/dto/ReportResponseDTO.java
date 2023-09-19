package com.neshan.project.dto;

import com.neshan.project.myEnum.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ReportResponseDTO {

    private Long id;
    private ReportType type;
    private String point;
    private Map<String, Object> additionalInformation;

}

