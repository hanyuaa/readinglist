package com.manning;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanyu on 2018/5/30.
 */
public enum ReportStatus{

    State0(0, "待处理"),
    State1(1, "已处理"),
    State2(2, "已驳回"),;

    private final int value;
    private final String message;

    public static ReportStatus fromValue(final int value) {
        for (ReportStatus reportStatus : ReportStatus.values()) {
            if (value == reportStatus.getValue()) {
                return reportStatus;
            }
        }

        return null;
    }

    public static List<ReportStatus> fromValues(final Iterable<Integer> codes) {
        List<ReportStatus> states = new ArrayList<>();

        for (Integer code : codes) {
            final ReportStatus reportStatus = fromValue(code);

            if (reportStatus != null) {
                states.add(reportStatus);
            }
        }
        return states;
    }

    ReportStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return message;
    }
}
