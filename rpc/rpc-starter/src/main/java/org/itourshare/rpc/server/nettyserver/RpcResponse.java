package org.itourshare.rpc.server.nettyserver;

import java.io.Serializable;

/**
 * @ClassName : RpcResponse
 * @Description :
 * @Author : its
 * @Date: 2020-08-24 15:31
 */
public class RpcResponse implements Serializable {

    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
