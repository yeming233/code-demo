import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName : TestProxy
 * @Description : 动态代理
 * @Author : its
 * @Date: 2020-08-19 16:59
 */
public class TestProxy {

    public static void main(String[] args) {
//        Print print = new PrintImpl();
        Print jdkDynamicProxy = createJdkDynamicProxy(null);
        String status = jdkDynamicProxy.colorPrint("red");
        System.out.println(status);
    }

    private static Print createJdkDynamicProxy(final Print delegate) {
        Print jdkProxy = (Print) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Print.class}, new JdkHandler(delegate));
        return jdkProxy;
    }

}

// 代理接口
interface Print {
    String colorPrint(String color);
}

//class PrintImpl implements Print{
//    @Override
//    public void colorPrint(String color) {
//        System.out.println("invoke colorPrint");
//    }
//}

class JdkHandler implements InvocationHandler {

    final Object delegate;

    JdkHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects)
            throws Throwable {
        if (method.getName().equals("colorPrint")) {
            System.out.println("colorPrint=======colorPrint");
        }
//        return method.invoke(delegate, objects);
        return "finish";
    }
}
