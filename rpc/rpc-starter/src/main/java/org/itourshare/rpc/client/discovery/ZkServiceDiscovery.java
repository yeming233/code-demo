package org.itourshare.rpc.client.discovery;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.ZkClient;
import org.itourshare.rpc.constants.CommonConstant;
import org.itourshare.rpc.protocol.Service;
import org.itourshare.rpc.protocol.ZookeeperSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName : ZkServiceDiscovery
 * @Description :
 * @Author : its
 * @Date: 2020-08-22 10:13
 */
public class ZkServiceDiscovery implements ServiceDiscovery {

    private static Logger logger = LoggerFactory.getLogger(ZkServiceDiscovery.class);

    private ZkClient zkClient;

    public ZkServiceDiscovery() {
    }

    public ZkServiceDiscovery(String address) {
        zkClient = new ZkClient(address);
        zkClient.setZkSerializer(new ZookeeperSerializer());
    }

    @Override
    public List<Service> getService(String serviceName) throws Exception {
        List<String> children = zkClient.getChildren(CommonConstant.createZkServicePath(serviceName));
        return children.stream().map(ch -> {
            String decode = null;
            try {
                decode = URLDecoder.decode(ch, CommonConstant.CHARSET_UTF8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return JSON.parseObject(decode, Service.class);
        }).collect(Collectors.toList());
    }
}
