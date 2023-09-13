package com.neshan.project.myEnum;

import lombok.Getter;

@Getter
public enum Side {
    SAME(1), OPPOSITE(0);

    private final int code;

    Side(int code) {
        this.code = code;
    }

    public static Side fromCode(int code) {
        return switch (code) {
            case 0 -> Side.OPPOSITE;
            case 1 -> Side.SAME;
            default -> throw new IllegalArgumentException("code [" + code
                    + "] not supported.");
        };
    }
}
