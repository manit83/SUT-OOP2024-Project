package com.example.demo1;

import java.time.LocalDateTime;

public class Info {
    private final String timeDate = "2024/07/12 14:09:34";
    private boolean win = true;
    private int op_l = 1;
    private String op_name = "ali_3";
    private int xp_gained = 100;
    Info(String timeDate, boolean win, int op_l, String op_name, int xp_gained){
//        this.timeDate = timeDate;
//        this.win = win;
//        this.op_l = op_l;
//        this.op_name = op_name;
//        this.xp_gained = xp_gained;
    }
    // get
    public String getTimeDate() {
        return timeDate;
    }

    public boolean isWin() {
        return win;
    }

    public int getOp_l() {
        return op_l;
    }

    public String getOp_name() {
        return op_name;
    }

    public int getXp_gained() {
        return xp_gained;
    }
}
