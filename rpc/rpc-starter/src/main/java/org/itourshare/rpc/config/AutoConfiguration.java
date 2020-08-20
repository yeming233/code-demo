package org.itourshare.rpc.config;

import org.itourshare.rpc.client.proxy.ProxyFactory;
import org.itourshare.rpc.server.register.DefaultRpcProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : AutoConfiguration
 * @Description :
 * @Author : its
 * @Date: 2020-08-19 16:24
 */
@Configuration
public class AutoConfiguration {

    @Bean
    public DefaultRpcProcessor defaultRpcProcessor(){
        return new DefaultRpcProcessor();
    }

    @Bean
    public ProxyFactory proxyFactory(){
        ProxyFactory proxyFactory = new ProxyFactory();
        return proxyFactory;
    }

}
