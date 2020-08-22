package org.itourshare.rpc.server.nettyserver;

public abstract class RpcServer {

    protected int port;

    public abstract void start();

    public abstract void stop();

}
