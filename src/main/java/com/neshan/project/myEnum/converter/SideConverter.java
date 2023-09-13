package com.neshan.project.myEnum.converter;

import com.neshan.project.myEnum.Side;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
