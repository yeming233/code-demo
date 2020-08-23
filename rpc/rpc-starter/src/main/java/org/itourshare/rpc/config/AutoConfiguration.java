package org.itourshare.rpc.config;

import org.itourshare.rpc.client.discovery.ServiceDiscovery;
import org.itourshare.rpc.client.discovery.ZkServiceDiscovery;
import org.itourshare.rpc.client.nettyclient.NettyRpcClient;
import org.itourshare.rpc.client.nettyclient.RequestHandler;
import org.itourshare.rpc.client.nettyclient.RpcClient;
import org.itourshare.rpc.client.proxy.ProxyFactory;
import org.itourshare.rpc.properties.RpcProperty;
import org.itourshare.rpc.protocol.Service;
import org.itourshare.rpc.server.nettyserver.NettyRpcServer;
import org.itourshare.rpc.server.nettyserver.ResponseHandler;
import org.itourshare.rpc.server.nettyserver.RpcHandler;
import org.itourshare.rpc.server.nettyserver.RpcServer;
import org.itourshare.rpc.server.register.DefaultRpcProcessor;
import org.itourshare.rpc.server.register.ServiceRegister;
import org.itourshare.rpc.server.register.ZkServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : AutoConfiguration
 * @Description : 注入bean
 * @Author : its
 * @Date: 2020-08-19 16:24
 */
@Configuration
public class AutoConfiguration {

    @Autowired
    private RpcProperty rpcProperty;

    @Bean
    public RpcProperty rpcProperty() {
        return new RpcProperty();
    }

    @Bean
    public DefaultRpcProcessor defaultRpcProcessor() {
        return new DefaultRpcProcessor();
    }

    @Bean
    public ProxyFactory proxyFactory() {
        ProxyFactory proxyFactory = new ProxyFactory(serviceDiscovery(), new NettyRpcClient());
        return proxyFactory;
    }

    @Bean
    public ServiceRegister serviceRegister() {
        return new ZkServiceRegister(rpcProperty.getAddress(), rpcProperty.getServerPort(), rpcProperty.getProtocol());
    }

    @Bean
    public ServiceDiscovery serviceDiscovery() {
        return new ZkServiceDiscovery(rpcProperty.getAddress());
    }

    @Bean
    public RpcServer rpcServer() {
        return new NettyRpcServer(rpcProperty.getServerPort(), rpcHandler());
    }

    @Bean
    public RpcHandler rpcHandler() {
        return new RpcHandler();
    }

    @Bean
    public ResponseHandler responseHandler() {
        return new ResponseHandler(serviceRegister());
    }
}
