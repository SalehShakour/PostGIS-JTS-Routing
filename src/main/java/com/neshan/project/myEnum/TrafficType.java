package com.neshan.project.myEnum;

import lombok.Getter;

@Getter
public enum TrafficType {
    LOCKED(0), SMOOTH(1), NORMAL(2);

    private final int code;

    TrafficType(int code) {
        this.code = code;
    }
    public static TrafficType fromCode(int code) {
        return switch (code) {
            case 0 -> TrafficType.LOCKED;
            case 1 -> TrafficType.NORMAL;
            case 2 -> TrafficType.SMOOTH;
            default -> throw new IllegalArgumentException("code [" + code
                    + "] not supported.");
        };
    }

}
