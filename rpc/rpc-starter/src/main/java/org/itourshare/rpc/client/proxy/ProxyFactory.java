package org.itourshare.rpc.client.proxy;

import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * @ClassName : ProxyFactory
 * @Description :
 * @Author : its
 * @Date: 2020-08-19 19:19
 */
public class ProxyFactory {

    private Map<Class<?>, Object> proxyCaChe = new HashMap<>();

    /***
     * @Param [clazz] 被代理类
     * @description 通过JAVA动态代理获取代理类
     * @author its
     * @date 2020/8/19 19:22
     * @return T 代理类
     * @throws
     */
    public <T> T getProxy(Class<T> clazz) {
        return (T) proxyCaChe.computeIfAbsent(clazz, cla -> newProxyInstance(cla.getClassLoader(), new Class<?>[]{cla}, new ProxyInvocationHandler(cla)));
    }

}
