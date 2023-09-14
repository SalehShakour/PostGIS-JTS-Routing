package com.neshan.project.myEnum.converter;

import com.neshan.project.myEnum.ReportType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReportTypeConverter implements AttributeConverter<ReportType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ReportType reportType) {
        return reportType.getCode();
    }

    @Override
    public ReportType convertToEntityAttribute(Integer integer) {
        return ReportType.fromCode(integer);
    }
}
