package org.itourshare.rpc.server.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : DefaultServiceRegister
 * @Description :
 * @Author : its
 * @Date: 2020-08-20 16:08
 */
public class DefaultServiceRegister implements ServiceRegister {

    public Map<String, ServiceObject> serviceCache = new HashMap<>();

    protected String protocol;

    /***
     * rpc server port
     */
    protected Integer port;

    @Override
    public void register(ServiceObject serviceObject) throws Exception {
        if (null == serviceObject) {
            return;
        }
        serviceCache.put(serviceObject.getName(), serviceObject);
    }

    @Override
    public ServiceObject getService(String serviceName) throws Exception {
        return serviceCache.get(serviceName);
    }
}
