package org.itourshare.rpc.server.register;

import org.itourshare.rpc.annotation.ProxyService;
import org.itourshare.rpc.annotation.RpcService;
import org.itourshare.rpc.client.proxy.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName : DefaultRpcProcessor
 * @Description :  ApplicationListener可以监听某个事件event,通过实现这个接口，传入一个泛型事件，在run方法中就可以监听这个事件，从而做出一定的逻辑
 * ContextRefreshedEvent: ApplicationContext被初始化或刷新时，该事件被发布
 * @Author : its
 * @Date: 2020-08-19 10:57
 */
public class DefaultRpcProcessor implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger logger = LoggerFactory.getLogger(DefaultRpcProcessor.class);

    @Autowired
    private ProxyFactory proxyFactory;

    @Autowired
    private ServiceRegister serviceRegister;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (Objects.isNull(contextRefreshedEvent.getApplicationContext().getParent())) {
            ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
            startRpcServer(applicationContext);
            startProxy(applicationContext);
        }

    }

    /**
     * 1.服务注册，
     * 2.启动netty server.
     */
    private void startRpcServer(ApplicationContext applicationContext) {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (beansWithAnnotation.size() != 0) {
            for (Object object : beansWithAnnotation.values()) {
                Class<?> clazz = object.getClass();
                Class<?>[] interfaces = clazz.getInterfaces();
                ServiceObject serviceObject = new ServiceObject();
                if (interfaces.length != 1) {
                    RpcService annotation = clazz.getAnnotation(RpcService.class);
                    String value = annotation.value();
                    if("".equals(value)){
                        throw new UnsupportedOperationException(String.format("Multiple Interface,{}", clazz.getName()));
                    }
                    try {
                        serviceObject = new ServiceObject(value, Class.forName(value), object);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    Class<?> superClass = interfaces[0];
                    serviceObject = new ServiceObject(superClass.getName(), superClass, object);
                }
                // 服务注册
                try {
                    serviceRegister.register(serviceObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // start netty server

        }
    }

    /**
     * 代理{@link ProxyService}注释的类
     */
    private void startProxy(ApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            Class<?> clazz = applicationContext.getType(name);
            if (Objects.isNull(clazz)) {
                continue;
            }
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                ProxyService annotation = field.getAnnotation(ProxyService.class);
                if (Objects.isNull(annotation)) {
                    continue;
                }
                Class<?> proxyClazz = field.getType();
                Object bean = applicationContext.getBean(name);
                field.setAccessible(true);
                try {
                    field.set(bean, proxyFactory.getProxy(proxyClazz));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
