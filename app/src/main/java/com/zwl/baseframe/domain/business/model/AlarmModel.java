package com.zwl.baseframe.domain.business.model;

/**
 * Created by hasee on 2017/6/26.
 */

public class AlarmModel {
    private int id;//1上午,2下午
    private int hour;
    private int minute;
    private boolean open;


    public void setOpen(boolean open) {
        this.open = open;
    }
}
