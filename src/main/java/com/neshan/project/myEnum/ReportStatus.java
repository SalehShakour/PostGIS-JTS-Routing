package com.neshan.project.myEnum;

import lombok.Getter;

@Getter
public enum ReportStatus {
    OPEN(1),PENDING(2),CLOSE(0);

    private final int code;

    ReportStatus(int code) {
        this.code = code;
    }
    public static ReportStatus fromCode(int code) {
        return switch (code) {
            case 0 -> ReportStatus.CLOSE;
            case 1 -> ReportStatus.OPEN;
            case 2 -> ReportStatus.PENDING;
            default -> throw new IllegalArgumentException("code [" + code
                    + "] not supported.");
        };
    }
}
