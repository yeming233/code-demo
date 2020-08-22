package org.itourshare.rpc.client.discovery;

import org.itourshare.rpc.protocol.Service;
import org.itourshare.rpc.server.register.ServiceObject;

import java.util.List;

/**
 * @ClassName : ServiceDiscovery
 * @Description : 服务发现
 * @Author : its
 * @Date: 2020-08-20 14:44
 */
public interface ServiceDiscovery {

    /***
     * 服务发现
     */
    List<Service> getService(String serviceName) throws Exception;

}
