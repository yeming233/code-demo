package org.itourshare.rpc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @ClassName : RpcProperty
 * @Description :
 * @Author : its
 * @Date: 2020-08-20 16:23
 */
@EnableConfigurationProperties(RpcProperty.class)
@ConfigurationProperties("rpc")
public class RpcProperty {

    private String address = "127.0.0.1:2181";

    private Integer serverPort = 9999;

    private String version = "V1";

    private String protocol = "rpc";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
