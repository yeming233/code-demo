package org.itourshare.rpc.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName : RpcService
 * @Description :
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
