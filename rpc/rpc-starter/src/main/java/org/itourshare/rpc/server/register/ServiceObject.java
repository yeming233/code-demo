package org.itourshare.rpc.server.register;

/**
 * @ClassName : ServiceObject
 * @Description : 服务注册详细信息
 * @Author : its
 * @Date: 2020-08-20 14:44
 */
public class ServiceObject {
    /***
     * 服务名
     */
    private String name;
    /***
     * 服务class
     */
    private Class<?> clazz;
    /***
     * 服务对象
     */
    private Object object;

    public ServiceObject() {
    }

    public ServiceObject(String name, Class<?> clazz, Object object) {
        this.name = name;
        this.clazz = clazz;
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
