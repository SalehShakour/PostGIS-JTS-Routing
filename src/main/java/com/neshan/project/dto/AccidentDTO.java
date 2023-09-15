package com.neshan.project.dto;

import com.neshan.project.myEnum.AccidentSeverity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccidentDTO(
        @NotNull @NotBlank PointDTO pointDTO,
        @NotNull @NotBlank AccidentSeverity severity
) {}
