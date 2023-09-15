package com.neshan.project.dto;

import com.neshan.project.myEnum.AccidentSeverity;
import com.neshan.project.myEnum.Side;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.locationtech.jts.geom.Point;

public record BumpDTO(@NotNull @NotBlank PointDTO pointDTO,
                      @NotNull @NotBlank Side side) {
}
