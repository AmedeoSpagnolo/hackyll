package com.msfrpc;

import com.amedeospagnolo.hack.MsfApplication;

public class Msf {

    public final MsfServerList msfServerList = new MsfServerList();

    public static Msf get() {
        return MsfApplication.Msf();
    }

}
