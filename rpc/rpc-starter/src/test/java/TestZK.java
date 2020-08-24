import com.alibaba.fastjson.JSONObject;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * @ClassName : TestZK
 * @Description :
 * @Author : its
 * @Date: 2020-08-24 20:34
 */
public class TestZK {
    public static void main(String[] args) {
        String address = "118.24.129.122:2222";
        ZkClient zkClient = new ZkClient(address);

        zkClient.subscribeDataChanges("/rpc", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("sub data change" + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("sub data del");
            }
        });

        List<String> rpc = zkClient.getChildren("/rpc");
        JSONObject json = new JSONObject();
        json.put("name","中文1");
        zkClient.writeData("/rpc",json);
        Object o = zkClient.readData("/rpc");
        System.out.println(o);

        System.out.println();
        
    }
}
