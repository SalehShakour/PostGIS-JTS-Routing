package com.neshan.project.myEnum.converter;

import com.neshan.project.myEnum.Side;
import com.neshan.project.myEnum.TrafficType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TrafficTypeConverter implements AttributeConverter<TrafficType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TrafficType trafficType) {
        return trafficType.getCode();
    }

    @Override
    public TrafficType convertToEntityAttribute(Integer integer) {
        return TrafficType.fromCode(integer);
    }
}
