package org.itourshare.rpc.protocol;

import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.nio.charset.StandardCharsets;

/**
 * Zookeeper序列化器
 */
public class ZookeeperSerializer implements ZkSerializer {

    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @return 对象
     */
    @Override
    public Object deserialize(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 序列化
     *
     * @param obj 对象
     * @return 字节数组
     */
    @Override
    public byte[] serialize(Object obj) {
        return String.valueOf(obj).getBytes(StandardCharsets.UTF_8);
    }
}
