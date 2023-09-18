package com.neshan.project.converter;

import com.neshan.project.myEnum.AccidentSeverity;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AccidentSeverityConverter implements AttributeConverter<AccidentSeverity, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AccidentSeverity accidentSeverity) {
        return accidentSeverity.getCode();
    }

    @Override
    public AccidentSeverity convertToEntityAttribute(Integer integer) {
        return AccidentSeverity.fromCode(integer);
    }
}
