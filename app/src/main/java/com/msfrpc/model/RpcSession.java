package com.msfrpc.model;


public class RpcSession  {

    public final Session session;
    public final RpcServer rpcServer;

    public RpcSession(RpcServer rpcServer, Session session) {
        this.session = session;
        this.rpcServer = rpcServer;
    }

    public String getInformation() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(session.description);
        stringBuilder.append(" ");
        String ip = (String)session.fields.get("tunnel_peer");
        stringBuilder.append(ip);
        return stringBuilder.toString();
    }
}
