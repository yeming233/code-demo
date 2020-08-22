package org.itourshare.rpc.server.nettyserver;

import org.itourshare.rpc.server.register.ServiceRegister;

/**
 * @ClassName : RequestHandler
 * @Description :
 * @Author : its
 * @Date: 2020-08-22 17:32
 */
public class RequestHandler {

    private ServiceRegister serviceRegister;

    public RequestHandler(ServiceRegister serviceRegister) {
        this.serviceRegister = serviceRegister;
    }

    public byte[] handle(byte[] bytes){
        return null;
    }

}
