package com.zwl.baseframe.domain.business.model;

/**
 * Created by hasee on 2017/6/26.
 */

public class AlarmModel {
    private long id;
    private int hour;
    private int minute;
    private boolean open;

    public AlarmModel(long id, int hour, int minute, boolean open) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.open = open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isOpen() {
        return open;
    }
}
