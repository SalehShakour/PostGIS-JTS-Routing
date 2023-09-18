package com.neshan.project.converter.converter;

import com.neshan.project.myEnum.Side;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SideConverter implements AttributeConverter<Side, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Side side) {
        return side.getCode();
    }

    @Override
    public Side convertToEntityAttribute(Integer code) {
        return Side.fromCode(code);
    }
}
