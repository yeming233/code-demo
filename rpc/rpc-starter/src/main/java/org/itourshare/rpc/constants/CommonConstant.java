package org.itourshare.rpc.constants;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.itourshare.rpc.protocol.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @ClassName : CommonConstant
 * @Description :
 * @Author : its
 * @Date: 2020-08-22 08:28
 */
public class CommonConstant {

    public static final String ZK_ROOT_PATH = "/rpc";

    public static final String ZK_PATH_SEPARATOR = "/";

    public static final String ZK_SERVICE_PATH = "/service";

    public static final String CHARSET_UTF8 = "UTF-8";


    public static String createZkServicePath(String serviceName) throws UnsupportedEncodingException {
        return String.format("%s%s%s%s", ZK_ROOT_PATH, ZK_PATH_SEPARATOR, serviceName, ZK_SERVICE_PATH);
    }

    public static String createZkServicePath(Service service) throws UnsupportedEncodingException {
        String uri = URLEncoder.encode(JSON.toJSONString(service), CHARSET_UTF8);
        return String.format("%s%s%s", createZkServicePath(service.getName()), ZK_PATH_SEPARATOR, uri);
    }
}
