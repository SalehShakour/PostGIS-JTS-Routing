package com.neshan.project.dto;

import com.neshan.project.myEnum.Side;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CameraDTO(@NotNull @NotBlank PointDTO pointDTO,
                        @NotNull @NotBlank Side side) {
}
