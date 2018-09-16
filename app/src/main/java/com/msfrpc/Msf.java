package com.msfrpc;

import com.amedeospagnolo.hack.MsfApplication;
import com.msfrpc.model.RpcServer;
import com.msfrpc.model.RpcSession;
import com.msfrpc.model.Session;
import com.msfrpc.rpc.RpcConnection;
import com.msfrpc.rpc.RpcException;

import java.util.ArrayList;
import java.util.List;

public class Msf {

    public final MsfServerList msfServerList = new MsfServerList();

    public static Msf get() {
        return MsfApplication.Msf();
    }

    public void updateModels() {
        for (RpcServer rpcServer : msfServerList.getServerList()) {
            if (rpcServer.status != RpcServer.STATUS_CONNECTED) {
                msfServerList.connectServer(rpcServer);
            }
            if (rpcServer.status == RpcServer.STATUS_CONNECTED) {
                try {
                    rpcServer.rpcConnection.updateModel();
                } catch (RpcException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<RpcSession> getAllSessions() {
        List<RpcSession> rpcSessionList = new ArrayList<>();
        for (RpcServer rpcServer : msfServerList.getServerList()) {
            RpcConnection rpcConnection = rpcServer.rpcConnection;
            if (rpcConnection == null) {
                continue;
            }
            for (Session session : rpcConnection.getModel().getSessions()) {
                rpcSessionList.add(new RpcSession(rpcServer, session));
            }
        }
        return rpcSessionList;
    }

}
