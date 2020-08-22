package org.itourshare.rpc.server.register;

import org.I0Itec.zkclient.ZkClient;
import org.itourshare.rpc.constants.CommonConstant;
import org.itourshare.rpc.protocol.Service;
import org.itourshare.rpc.protocol.ZookeeperSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;

/**
 * @ClassName : ZkServiceRegister
 * @Description :
 * @Author : its
 * @Date: 2020-08-20 16:11
 */
public class ZkServiceRegister extends DefaultServiceRegister {
    private static Logger logger = LoggerFactory.getLogger(ZkServiceRegister.class);

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
    public void register(ServiceObject serviceObject) throws Exception {
        super.register(serviceObject);
        Service service = new Service();
        service.setName(serviceObject.getName());
        service.setAddress(InetAddress.getLocalHost().getHostAddress() + ":" + port);
        service.setProtocol(protocol);
        exportService(service);
    }

    /***
     * @Param [service]
     * @description 注册到zk
     * @author its
     * @date 2020/8/22 8:24
     * @return void
     * @throws
     */
    private void exportService(Service service) throws UnsupportedEncodingException {
        String zkServicePath = CommonConstant.createZkServicePath(service.getName());
        if (!zkClient.exists(zkServicePath)) {
            zkClient.createPersistent(zkServicePath, true);
        }
        String zkUriPath = CommonConstant.createZkServicePath(service);
        if (zkClient.exists(zkUriPath)) {
            zkClient.delete(zkUriPath);
        }
        zkClient.createEphemeral(zkUriPath);
        logger.info("register to zk,path [{}]", zkServicePath);
        logger.info("register to zk,path [{}]", zkUriPath);
    }
}
