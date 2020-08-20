package org.itourshare.rpc.protocol;

/**
 * @ClassName : Service
 * @Description : 服务注册暴露信息
 * @Author : its
 * @Date: 2020-08-20 16:16
 */
public class Service {

    /***
     * 服务名
     */
    private String name;
    /***
     * 协议
     */
    private String protocol;
    /***
     * 服务地址，IP:PORT
     */
    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
