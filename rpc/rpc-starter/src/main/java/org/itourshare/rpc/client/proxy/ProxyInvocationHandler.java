package org.itourshare.rpc.client.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName : ProxyInvocationHandler
 * @Description : 代理类，在invoke方法中执行远程调用。
 * @Author : its
 * @Date: 2020-08-19 19:27
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private Class<?> clazz;

    public ProxyInvocationHandler(Class<?> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
