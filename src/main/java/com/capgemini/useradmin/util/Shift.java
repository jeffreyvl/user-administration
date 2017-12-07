package com.capgemini.useradmin.util;

public enum Shift {
    MORNING(6),
    AFTERNOON(6),
    EVENING(6),
    NIGHT(6);

    private int duration;

    Shift(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}
