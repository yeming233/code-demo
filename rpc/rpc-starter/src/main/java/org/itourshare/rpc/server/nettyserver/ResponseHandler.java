package org.itourshare.rpc.server.nettyserver;

import com.alibaba.fastjson.JSON;
import org.itourshare.rpc.client.nettyclient.RpcRequest;
import org.itourshare.rpc.server.register.ServiceObject;
import org.itourshare.rpc.server.register.ServiceRegister;

import java.lang.reflect.Method;

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

    public byte[] handle(RpcRequest rpcRequest) throws Exception{
        ServiceObject service = serviceRegister.getService(rpcRequest.getServiceName());
        if(null == service){
            throw new RuntimeException("service not found");
        }
        Method method = service.getClazz().getMethod(rpcRequest.getMethod(), rpcRequest.getParameterTypes());
        Object invoke = method.invoke(service.getObject(), rpcRequest.getParameters());
        RpcResponse response = new RpcResponse();
        response.setResult(invoke);
        return JSON.toJSONBytes(response);
    }

}
