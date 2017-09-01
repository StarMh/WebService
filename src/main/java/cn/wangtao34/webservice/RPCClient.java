package cn.wangtao34.webservice;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import cn.wangtao34.user.User;

/**
 * 
 * @author wangtao34
 * @time 2017年8月16日 下午5:00:18
 */
/**
 * 方法二： 应用rpc的方式调用 这种方式就等于远程调用， 即通过url定位告诉远程服务器，告知方法名称，参数等， 调用远程服务，得到结果。 使用
 * org.apache.axis2.rpc.client.RPCServiceClient类调用WebService
 * 
 * 【注】：
 * 
 * 如果被调用的WebService方法有返回值 应使用 invokeBlocking 方法 该方法有三个参数
 * 第一个参数的类型是QName对象，表示要调用的方法名； 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；
 * 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。
 * 第三个参数表示WebService方法的 返回值类型的Class对象，参数类型为Class[]。
 * 
 * 
 * 如果被调用的WebService方法没有返回值 应使用 invokeRobust 方法
 * 该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同。
 * 
 * 在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名， 也就是
 * <wsdl:definitions>元素的targetNamespace属性值。
 * 
 */
public class RPCClient {
	private static EndpointReference targetEPR = new EndpointReference(
			"http://10.11.162.102:8080/axis2/services/MyService");

	/**
	 * 调用hello
	 * 
	 * @author wangtao34
	 * @time 2017年8月17日 下午4:08:58
	 * @param name
	 * @return
	 */
	public static String hello(String name) {
		// 使用RPC方式调用WebService
		RPCServiceClient serviceClient = null;
		try {
			serviceClient = new RPCServiceClient();
		} catch (AxisFault e) {
			e.printStackTrace();
			return "ERROR1";
		}
		Options options = serviceClient.getOptions();
		// 确定目标服务地址
		options.setTo(targetEPR);
		// 确定调用方法
		options.setAction("urn:sayHello");
		/**
		 * 指定要调用的getPrice方法及WSDL文件的命名空间 如果 webservice 服务端由axis2编写 命名空间 不一致导致的问题
		 * org.apache.axis2.AxisFault: java.lang.RuntimeException: Unexpected
		 * subelement arg0
		 */
		QName qname = new QName("http://server.webservice.wangtao34.cn", "sayHello");
		// 指定getPrice方法的参数值
		Object[] parameters = new Object[] { name };

		// 指定getPrice方法返回值的数据类型的Class对象
		// Class[] returnTypes = new Class[] { String.class };

		// 调用方法一 传递参数，调用服务，获取服务返回结果集
		OMElement element = null;
		try {
			element = serviceClient.invokeBlocking(qname, parameters);
		} catch (AxisFault e) {
			e.printStackTrace();
			return "ERROR2";
		}
		// 值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。
		// 我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果
		String result = element.getFirstElement().getText();
		return result;
	}

	/**
	 * 调用获取用户信息
	 * 
	 * @author wangtao34
	 * @time 2017年8月17日 下午4:09:14
	 * @param user
	 * @return
	 */
	public static User userInfo(User user) {
		// 使用RPC方式调用WebService
		User errUser = new User();
		errUser.setName("ERROR");
		errUser.setGender("ERROE");
		errUser.setAge(0);
		RPCServiceClient serviceClient = null;
		try {
			serviceClient = new RPCServiceClient();
		} catch (AxisFault e) {
			e.printStackTrace();
			return errUser;
		}
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
		options.setAction("urn:userInfo");
		QName qname = new QName("http://server.webservice.wangtao34.cn", "userInfo");
		Object[] parameters = new Object[] { user };
		Class[] rawtypes = new Class[] { User.class };
		Object[] response = null;
		try {
			response = serviceClient.invokeBlocking(qname, parameters, rawtypes);
		} catch (AxisFault e) {
			e.printStackTrace();
			return errUser;
		}
		User effUser = (User) response[0];
		return effUser;
	}
	/**
	 * 
	 * 
	 * @author wangtao34
	 * @time 2017年8月17日 下午4:09:35
	 * @param number
	 * @return
	 */
	public static Integer[] fe(Integer number) {
		Integer[] result = new Integer[1];
		RPCServiceClient serviceClient = null;
		try {
			serviceClient = new RPCServiceClient();
		} catch (AxisFault e) {
			e.printStackTrace();
			return result;
		}
		Options options = serviceClient.getOptions();
		options.setTo(targetEPR);
		options.setAction("urn:fe");
		QName qname = new QName("http://server.webservice.wangtao34.cn", "fe");
		Object[] parameters = new Object[] { number };
		Class[] rawtypes = new Class[] { Integer[].class };
		Object[] response = null;
		try {
			response = serviceClient.invokeBlocking(qname, parameters, rawtypes);
		} catch (AxisFault e) {
			e.printStackTrace();
			return result;
		}
		result = (Integer[]) response[0];
		return result;
	}
}
