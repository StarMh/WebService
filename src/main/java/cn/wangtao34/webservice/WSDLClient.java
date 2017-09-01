package cn.wangtao34.webservice;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import cn.wangtao34.webservice.server.MyService;
import cn.wangtao34.webservice.server.MyServiceStub;
import cn.wangtao34.webservice.server.SayHello;
import cn.wangtao34.webservice.server.SayHelloResponse;

/**
 * 
 * @author wangtao34
 * @time 2017年8月16日 下午5:18:55
 */
public class WSDLClient {
	public static String hello(String name) {
		MyService myService = null;
		try {
			myService = new MyServiceStub();
		} catch (AxisFault e) {
			e.printStackTrace();
			return "ERROR";
		}

		// new一个调用sayHello方法需要的参数SayHello，并且设置name
		SayHello sayHello = new SayHello();
		sayHello.setName(name);
		// 调用web服务
		SayHelloResponse sayHelloResponse = null;
		try {
			sayHelloResponse = myService.sayHello(sayHello);
		} catch (RemoteException e) {
			e.printStackTrace();
			return "ERROR";
		}
		// 拿到返回结果
		return sayHelloResponse.get_return();
	}
}
