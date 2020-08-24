package org.itourshare.rpc.server.register;

/**
 * @ClassName : ServiceRegister
 * @Description : 服务注册器
 * @Author : its
 * @Date: 2020-08-20 14:44
 */
public interface ServiceRegister {

    /***
     * 服务注册
     */
    void register(ServiceObject serviceObject) throws Exception;

    ServiceObject getService(String serviceName) throws Exception;
}
