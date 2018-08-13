package com.manning.logic;

import java.util.EnumSet;

/**
 */
public enum UserRole {

    C(1, "C"),
    B(2, "B"),;


    private final int code;
    private final String message;


    public static UserRole fromValue(final int code) {
        for (UserRole bonusAuditState : UserRole.values()) {
            if (code == bonusAuditState.getValue()) {
                return bonusAuditState;
            }
        }

        return null;
    }

    public static EnumSet<UserRole> fromValues(final Iterable<Integer> codes) {

        EnumSet<UserRole> bonusAuditStates = EnumSet.noneOf(UserRole.class);

        for (Integer code : codes) {

            final UserRole bonusAuditState = fromValue(code);

            if (bonusAuditState != null) {
                bonusAuditStates.add(bonusAuditState);
            }
        }

        return bonusAuditStates;
    }

    UserRole(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getValue() {
        return this.code;
    }

    public String getDescription() {
        return this.message;
    }
}
