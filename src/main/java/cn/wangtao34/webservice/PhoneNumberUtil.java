package cn.wangtao34.webservice;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HttpTransportProperties.ProxyProperties;

/**
 * 
 * @author wangtao34
 * @time 2017年8月16日 下午5:30:23
 */
public class PhoneNumberUtil {
	private static EndpointReference targetEPR = new EndpointReference(
            "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx");

    private static String getResult(String number) throws Exception
    {
        ServiceClient sender = new ServiceClient();
        sender.setOptions(buildOptions());
        OMElement result = sender.sendReceive(buildParam(number));
        return result.getFirstElement().getText();
    }

    private static OMElement buildParam(String number)
    {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://WebXml.com.cn/", "");
        OMElement data = fac.createOMElement("getMobileCodeInfo", omNs);
        OMElement inner = fac.createOMElement("mobileCode", omNs);
        inner.setText(number);
        data.addChild(inner);
        return data;
    }

    private static Options buildOptions()
    {
        Options options = new Options();
        options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
        options.setAction("http://WebXml.com.cn/getMobileCodeInfo");
        options.setTo(targetEPR);
        //options.setProperty 如果不是通过代理上网，此句可省
//        options.setProperty(HTTPConstants.PROXY, buildProxy());
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
        return options;
    }

    /**
     * 本机采用代理服务器上网时，需要设置代理
     * @return
     */
    private static ProxyProperties buildProxy()
    {
        ProxyProperties proxyProperties = new ProxyProperties();
        proxyProperties.setProxyName("10.11.162.1");
        proxyProperties.setProxyPort(8080);
        return proxyProperties;
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println(getResult("13250801660"));
    }
    public static String phoneNumber(String number){
    	String result = null;
    	try{
    		result = getResult(number);
    	} catch(Exception e){
    		return "外部服务连接错误！";
    	}
    	return result;
    }

}
