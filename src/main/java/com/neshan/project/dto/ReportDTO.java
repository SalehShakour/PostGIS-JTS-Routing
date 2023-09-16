package com.neshan.project.dto;

import com.neshan.project.myEnum.ReportType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReportDTO(@NotNull @NotBlank ReportType type,
                        String point,
                        String config
                        ) {

}
