package com.msfrpc.rpc;

public class LoginException extends com.msfrpc.rpc.RpcException {
    public static final String LOGIN_FAILED = "Login Failed";

    public LoginException() {
        super(LOGIN_FAILED);
    }
}
