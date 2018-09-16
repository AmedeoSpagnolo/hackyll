package com.msfrpc.model;


public class RpcSession  {

    public final Session session;
    public final RpcServer rpcServer;

    public RpcSession(RpcServer rpcServer, Session session) {
        this.session = session;
        this.rpcServer = rpcServer;
    }
}
