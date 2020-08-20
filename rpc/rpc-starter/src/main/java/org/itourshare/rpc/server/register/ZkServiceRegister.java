package org.itourshare.rpc.server.register;

import org.I0Itec.zkclient.ZkClient;
import org.itourshare.rpc.protocol.Service;
import org.itourshare.rpc.protocol.ZookeeperSerializer;

import java.net.InetAddress;

/**
 * @ClassName : ZkServiceRegister
 * @Description :
 * @Author : its
 * @Date: 2020-08-20 16:11
 */
public class ZkServiceRegister extends DefaultServiceRegister {

    private ZkClient zkClient;

    public ZkServiceRegister() {
    }

    public ZkServiceRegister(String address, Integer port, String protocol) {
        this.protocol = protocol;
        this.port = port;
        zkClient = new ZkClient(address);
        zkClient.setZkSerializer(new ZookeeperSerializer());
    }

    @Override
    public void register(ServiceObject serviceObject) throws Exception{
        super.register(serviceObject);
        Service service = new Service();
        service.setName(serviceObject.getClazz().getName());
        service.setAddress(InetAddress.getLocalHost() + ":" + port);
        service.setProtocol(protocol);
        exportService(service);
    }

    private void exportService(Service service){

    }
}
