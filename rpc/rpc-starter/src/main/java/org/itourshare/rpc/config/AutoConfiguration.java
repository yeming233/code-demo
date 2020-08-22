package org.itourshare.rpc.config;

import org.itourshare.rpc.client.discovery.ServiceDiscovery;
import org.itourshare.rpc.client.discovery.ZkServiceDiscovery;
import org.itourshare.rpc.client.proxy.ProxyFactory;
import org.itourshare.rpc.properties.RpcProperty;
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
        ProxyFactory proxyFactory = new ProxyFactory(serviceDiscovery());
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
}
