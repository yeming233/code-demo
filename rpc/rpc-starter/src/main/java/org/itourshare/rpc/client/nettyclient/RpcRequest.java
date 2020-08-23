package org.itourshare.rpc.client.nettyclient;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RpcRequest implements Serializable {

    private String serviceName;

    private String method;

    private Map<String, String> headers = new HashMap<>();

    private Class<?>[] parameterTypes;

    private Object[] parameters;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getHeader(String name) {
        return this.headers == null ? null : this.headers.get(name);
    }

    public Object[] getParameters() {
        return this.parameters;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "serviceName='" + serviceName + '\'' +
                ", method='" + method + '\'' +
                ", headers=" + headers +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
