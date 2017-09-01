package cn.wangtao34.webservice;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMXMLBuilderFactory;
import org.apache.axiom.om.OMXMLParserWrapper;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.axis2.util.StreamWrapper;

import cn.wangtao34.user.User;

/**
 * 
 * @author wangtao34
 * @time 2017年8月16日 下午3:34:42
 */
public class AxiomClient {
	private static EndpointReference targetEPR = new EndpointReference(
			"http://10.11.162.102:8080/axis2/services/MyService");
	/**
	 * hello服务
	 * @author wangtao34
	 * @time 2017年8月17日 上午11:34:08
	 * @param name
	 * @return
	 */
	public static String hello(String name) {
		try {
			OMElement sayHello = sayHello(name);
			Options options = new Options();
			options.setTo(targetEPR);
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMElement result = sender.sendReceive(sayHello);
			return result.getFirstElement().getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
	/**
	 * 查询用户服务
	 * @author wangtao34
	 * @time 2017年8月17日 上午11:34:18
	 * @param user
	 * @return
	 */
	public static User getUser(User user){
		try {
			OMElement userInfo = userInfo(user);
			Options options = new Options();
			options.setTo(targetEPR);
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMElement result = sender.sendReceive(userInfo);
			System.out.println(result.getFirstElement().getText());
			User effUser = new User();
			effUser.setName("1");
			effUser.setGender("2");
			effUser.setAge(3);
			return effUser;
		} catch (Exception e) {
			e.printStackTrace();
			User errUser = new User();
			errUser.setName("ERROR");
			errUser.setGender("ERROE");
			errUser.setAge(0);
			return errUser;
		}
	}
	private static OMElement userInfo(User user) {
		OMFactory omFactory = OMAbstractFactory.getOMFactory();
		OMNamespace namespace = omFactory.createOMNamespace("http://server.webservice.wangtao34.cn", "");
		OMElement method = omFactory.createOMElement("userInfo", namespace);
		OMElement inner = castElement(user);
		method.addChild(inner);
		return method;
	}

	// 把user对象转化成相应的omElment对象
	public static OMElement castElement(User user) {
		javax.xml.stream.XMLStreamReader reader = BeanUtil.getPullParser(user);
		StreamWrapper parser = new StreamWrapper(reader);
		OMXMLParserWrapper stAXOMBuilder = OMXMLBuilderFactory.createStAXOMBuilder(OMAbstractFactory.getOMFactory(), parser);
		OMElement element = stAXOMBuilder.getDocumentElement();
		System.out.println("reader:"+reader);
		System.out.println("parser:"+parser);
		System.out.println("stAXOMBuilder:"+stAXOMBuilder);
		System.out.println("element:"+element);
		return element;
	}

	private static OMElement sayHello(String name) {
		OMFactory omFactory = OMAbstractFactory.getOMFactory();
		OMNamespace namespace = omFactory.createOMNamespace("http://server.webservice.wangtao34.cn", "");
		OMElement method = omFactory.createOMElement("sayHello", namespace);
		OMElement inner = omFactory.createOMElement("name", namespace);
		inner.setText(name);
		method.addChild(inner);
		return method;
	}

	public static void main(String[] args) {
		System.out.println(hello("wwww"));
	}

}
