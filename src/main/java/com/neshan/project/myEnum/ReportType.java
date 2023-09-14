package com.neshan.project.myEnum;

import lombok.Getter;

@Getter
public enum ReportType {
    ACCIDENT(0),TRAFFIC(1),BUMP(2),CAMERA(3),POLICE(4);

    private final int code;

    ReportType(int code) {
        this.code = code;
    }
    public static ReportType fromCode(int code) {
        return switch (code) {
            case 0 -> ReportType.ACCIDENT;
            case 1 -> ReportType.TRAFFIC;
            case 2 -> ReportType.BUMP;
            case 3 -> ReportType.CAMERA;
            case 4 -> ReportType.POLICE;
            default -> throw new IllegalArgumentException("code [" + code
                    + "] not supported.");
        };
    }
}
