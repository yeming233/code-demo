package org.itourshare.rpc.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName : RpcService
 * @Description : 注释被代理类，用于远程调用。
 * @Author : its
 * @Date: 2020-08-19 16:10
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RpcService {
    String value() default "";
}
