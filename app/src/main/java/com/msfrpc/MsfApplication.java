package com.msfrpc;


import android.app.Application;

import com.msfrpc.Msf;

public class MsfApplication extends Application {

    private static MsfApplication sInstance;
    private static Msf msf;

    public static MsfApplication getApplication() {
        return sInstance;
    }

    public static Msf Msf() {
        return msf;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        msf = new Msf();
    }

}
