package com.neshan.project.myEnum;

import lombok.Getter;

@Getter
public enum AccidentSeverity {
    HEAVY(1),LIGHT(0);

    private final int code;

    AccidentSeverity(int code) {
        this.code = code;
    }
    public static AccidentSeverity fromCode(int code) {
        return switch (code) {
            case 0 -> AccidentSeverity.LIGHT;
            case 1 -> AccidentSeverity.HEAVY;
            default -> throw new IllegalArgumentException("code [" + code
                    + "] not supported.");
        };
    }
}
