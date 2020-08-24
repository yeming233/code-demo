package com.itourshare.consumer.config;

import org.itourshare.rpc.server.register.DefaultRpcProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : AutoConfiguration
 * @Description :
 * @Author : its
 * @Date: 2020-08-24 20:25
 */
@Configuration
public class AutoConfiguration {

    @Bean
    public DefaultRpcProcessor defaultRpcProcessor() {
        return new DefaultRpcProcessor();
    }

}
