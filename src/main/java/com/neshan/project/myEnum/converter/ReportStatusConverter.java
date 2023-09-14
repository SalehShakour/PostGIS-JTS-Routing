package com.neshan.project.myEnum.converter;

import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.myEnum.Side;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReportStatusConverter implements AttributeConverter<ReportStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ReportStatus status) {
        return status.getCode();
    }

    @Override
    public ReportStatus convertToEntityAttribute(Integer integer) {
        return ReportStatus.fromCode(integer);
    }
}
