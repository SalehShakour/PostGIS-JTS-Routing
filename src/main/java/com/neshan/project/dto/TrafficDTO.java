package com.neshan.project.dto;

import com.neshan.project.myEnum.Side;
import com.neshan.project.myEnum.TrafficType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TrafficDTO(@NotNull @NotBlank PointDTO pointDTO,
                         @NotNull @NotBlank Side side,
                         @NotNull @NotBlank TrafficType trafficType) {
}
