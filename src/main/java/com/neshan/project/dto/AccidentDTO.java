package com.neshan.project.dto;

import com.neshan.project.myEnum.AccidentSeverity;
import com.neshan.project.myEnum.Side;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccidentDTO(
        @NotNull @NotBlank PointDTO pointDTO,
        @NotNull @NotBlank AccidentSeverity severity,
        @NotNull @NotBlank Side side
) {}
