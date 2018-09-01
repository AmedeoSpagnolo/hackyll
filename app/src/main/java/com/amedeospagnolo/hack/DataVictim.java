package com.amedeospagnolo.hack;

import android.graphics.drawable.Drawable;

public class DataVictim {
    public String name;
    public String ip;
    public String time;
    public Drawable avatar;

    public DataVictim(String name, String ip, String time, Drawable avatar) {
        this.name = name;
        this.ip = ip;
        this.time = time;
        this.avatar = avatar;
    }
}