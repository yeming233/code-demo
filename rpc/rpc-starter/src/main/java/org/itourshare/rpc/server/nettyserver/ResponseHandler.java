package org.itourshare.rpc.server.nettyserver;

import org.itourshare.rpc.client.nettyclient.RpcRequest;
import org.itourshare.rpc.server.register.ServiceRegister;

/**
 * @ClassName : RequestHandler
 * @Description :
 * @Author : its
 * @Date: 2020-08-22 17:32
 */
public class ResponseHandler {

    private ServiceRegister serviceRegister;

    public ResponseHandler(ServiceRegister serviceRegister) {
        this.serviceRegister = serviceRegister;
    }

    public byte[] handle(RpcRequest rpcRequest){
        return null;
    }

}
