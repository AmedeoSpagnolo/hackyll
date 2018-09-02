package com.amedeospagnolo.hack;

import android.graphics.drawable.Drawable;

public class DataServer {
    public String name;
    public String ip;
    public String time;
    public Drawable avatar;
    public Boolean has_shop;

    public DataServer(String name, String ip, String time, Drawable avatar, Boolean has_shop) {
        this.name = name;
        this.ip = ip;
        this.time = time;
        this.avatar = avatar;
        this.has_shop = has_shop;
    }
}