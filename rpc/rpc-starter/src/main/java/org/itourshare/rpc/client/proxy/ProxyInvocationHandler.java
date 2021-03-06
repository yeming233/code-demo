package org.itourshare.rpc.client.proxy;

import com.alibaba.fastjson.JSON;
import org.itourshare.rpc.client.discovery.ServiceDiscovery;
import org.itourshare.rpc.client.nettyclient.RpcClient;
import org.itourshare.rpc.client.nettyclient.RpcRequest;
import org.itourshare.rpc.protocol.Service;
import org.itourshare.rpc.server.nettyserver.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @ClassName : ProxyInvocationHandler
 * @Description : 代理类，在invoke方法中执行远程调用。
 * @Author : its
 * @Date: 2020-08-19 19:27
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private static Logger logger = LoggerFactory.getLogger(ProxyInvocationHandler.class);

    private ServiceDiscovery serviceDiscovery;

    private RpcClient rpcClient;

    private Class<?> clazz;

    public ProxyInvocationHandler(Class<?> clazz, ServiceDiscovery serviceDiscovery, RpcClient rpcClient) {
        super();
        this.clazz = clazz;
        this.serviceDiscovery = serviceDiscovery;
        this.rpcClient = rpcClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Service> services = serviceDiscovery.getService(clazz.getSimpleName());
        if (Objects.isNull(services)) {
            logger.info("no available service for {}", clazz.getSimpleName());
            throw new RuntimeException("no available service for " + clazz.getSimpleName());
        }
        Service service = services.get(new Random().nextInt(services.size()));
        logger.info("available service for {}->{}", clazz.getSimpleName(), service.toString());

        RpcRequest req = new RpcRequest();
        req.setServiceName(service.getName());
        req.setMethod(method.getName());
        req.setParameterTypes(method.getParameterTypes());
        req.setParameters(args);

//        service.setAddress("192.168.0.114:19999");
        byte[] resp = rpcClient.send(req, service);
        logger.info("response [{}]", resp);
        RpcResponse rpcResponse = JSON.parseObject(resp, RpcResponse.class);
        Object o = null;
        logger.info("response [{}]", rpcResponse.getResult());
        if (null != rpcResponse.getResult()) {
            o = JSON.parseObject(rpcResponse.getResult().toString(), method.getReturnType());
        }
        return o;
    }
}
