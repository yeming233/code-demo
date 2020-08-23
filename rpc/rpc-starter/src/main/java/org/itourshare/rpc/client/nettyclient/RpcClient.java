package org.itourshare.rpc.client.nettyclient;

import org.itourshare.rpc.protocol.Service;

public interface RpcClient {

    public void send(RpcRequest rpcRequest, Service service) throws Exception;

}
