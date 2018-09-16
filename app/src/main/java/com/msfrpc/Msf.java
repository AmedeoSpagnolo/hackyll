package com.msfrpc;

public class Msf {

    public final MsfServerList msfServerList = new MsfServerList();

    public static Msf get() {
        return MsfApplication.Msf();
    }

}
