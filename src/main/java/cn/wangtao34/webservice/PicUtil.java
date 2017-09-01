package cn.wangtao34.webservice;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.io.FileUtils;
import org.springframework.web.context.ContextLoader;

/**
 * 
 * @author wangtao34
 * @time 2017年8月16日 下午5:30:23
 */
public class PicUtil {
	private static EndpointReference targetEPR = new EndpointReference(
			"http://10.11.162.102:8080/30WebServicePut/services/ImageService");

	/**
	 * 
	 * @author wangtao34
	 * @time 2017年8月17日 下午7:10:16
	 * @param name
	 * @return
	 */
	public static String pic() {
		// 使用RPC方式调用WebService
		RPCServiceClient serviceClient = null;
		try {
			serviceClient = new RPCServiceClient();
		} catch (AxisFault e) {
			e.printStackTrace();
			return "error/ERROR";
		}
		Options options = serviceClient.getOptions();
		// 确定目标服务地址
		options.setTo(targetEPR);
		options.setAction("urn:generateVerifyCode");
		
		QName qname = new QName("http://pic.webservice.wangtao34.cn", "generateVerifyCode");
		Object[] parameters = new Object[] {4};
		Class[] rawtypes = new Class[] { String.class };
		Object[] response = null;
		try {
			response = serviceClient.invokeBlocking(qname, parameters, rawtypes);
		} catch (AxisFault e) {
			e.printStackTrace();
			return "error/ERROR";
		}
		String name = (String)response[0];
		
		// 第二步
		// 确定调用方法
		options.setAction("urn:outputByte");
		/**
		 * 指定要调用的getPrice方法及WSDL文件的命名空间 如果 webservice 服务端由axis2编写 命名空间 不一致导致的问题
		 * org.apache.axis2.AxisFault: java.lang.RuntimeException: Unexpected
		 * subelement arg0
		 */
		qname = new QName("http://pic.webservice.wangtao34.cn", "outputByte");
		// 指定getPrice方法的参数值
		parameters = new Object[] {name};

		rawtypes = new Class[] { byte[].class };
		response = null;
		try {
			response = serviceClient.invokeBlocking(qname, parameters, rawtypes);
		} catch (AxisFault e) {
			e.printStackTrace();
			return "error/ERROR";
		}
		byte[] b = (byte[]) response[0];
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(b);
			BufferedImage image = ImageIO.read(in);

			String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
			FileUtils.cleanDirectory(new File(path +"images"));
			File outputFile = new File(path +"images/" + name + ".jpg");
			FileOutputStream fos = new FileOutputStream(outputFile);
			ImageIO.write(image, "jpg", fos);
			in.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "error/ERROR";
		}
		return name;
	}
}
