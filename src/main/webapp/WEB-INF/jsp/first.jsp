<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="resources/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	function send(name) {
		if ($("#message" + "_" + name).val() == "") {
			$("#message" + "_" + name).css("border", "1px solid red");
			return;
		} else {
			$("#message" + "_" + name).css("border", "1px solid gray");
		}
		$.ajax({
			type : 'post',
			url : name,
			dataType : 'text',
			data : {
				"name" : $("#message" + "_" + name).val()
			},
			success : function(data) {
				$("#status" + "_" + name).html(
						"<font color=green>" + data + "</font>");
			},
			error : function(data) {
				$("#status" + "_" + name).html(
						"<font color=red>ERROR:" + data["status"] + ","
								+ data["statusText"] + "</font>");
			}

		});
	}
</script>
</head>
<body>
	<h1>一、发布</h1>
	<h2>查看发布的WebService服务</h2>
	<h2>1.Axis2插件自动生成：</h2>
	<h3>
		-功能：hello,加法，生成斐波那契数列，处理User类 <br> -点击链接查看WSDL文件： <br>- <a
			href="http://10.11.162.102:8080/axis2/services/MyService?wsdl"
			target="blank">http://10.11.162.102:8080/axis2/services/MyService?wsdl</a>
		<br>
	</h3>
	<h2>2.在项目中生成:</h2>
	<h3>
		-功能：生成随机验证码（字符串），生成验证码图片（字节流）<br> -点击链接查看WSDL文件：<br>- <a
			href="http://10.11.162.102:8080/30WebServicePut/services/ImageService?wsdl"
			target="blank">http://10.11.162.102:8080/30WebServicePut/services/ImageService?wsdl</a><br>
	</h3>
	<h1>二、调用</h1>
	<h2>1.Axiom 方式调用WebService的sayHello(String name)方法：</h2>
	<h3>输入用户名：</h3>
	<textarea id="message_axiomHello"></textarea>
	<button onclick="send('axiomHello')">确认</button>
	<br>
	<h3>
		WebService返回结果：<span id="status_axiomHello"></span>
	</h3>

	<h2>2.RPC方式调用WebService的sayHello(String name)方法：</h2>
	<h3>输入用户名：</h3>
	<textarea id="message_RPCHello"></textarea>
	<button onclick="send('RPCHello')">确认</button>
	<br>
	<h3>
		WebService返回结果：<span id="status_RPCHello"></span>
	</h3>

	<h2>3.通过wsdl2java反向生成的类调用WebService的sayHello(String name)方法：</h2>
	<h3>输入用户名：</h3>
	<textarea id="message_WSDLHello"></textarea>
	<button onclick="send('WSDLHello')">确认</button>
	<br>
	<h3>
		WebService返回结果：<span id="status_WSDLHello"></span>
	</h3>

	<h2>4.自定义类作为Web Service的调用参数、返回值</h2>
	<form:form method="GET" action="user">
		<table>
			<tr>
				<td><input type="submit" value="打开实例页面(用户注册)" /></td>
			</tr>
		</table>
	</form:form>
	<h2>5.数组作为返回值</h2>
	<form:form method="GET" action="fe">
		<table>
			<tr>
				<td><input type="submit" value="打开实例页面(生成斐波那契数列)" /></td>
			</tr>
		</table>
	</form:form>
	<h2>6.byte[]作为返回值并转化为图片</h2>
	<form:form method="GET" action="image">
		<table>
			<tr>
				<td><input type="submit" value="打开实例页面(生成验证码图片)" /></td>
			</tr>
		</table>
	</form:form>

	<h1>二、调用网络上提供的服务</h1>
	<h2>1.查询电话号码归属地：</h2>
	<a href="http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx"
		target="blank">http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx</a>
	<br>
	<h3>输入电话号码：</h3>
	<textarea id="message_phoneNumber"></textarea>
	<button onclick="send('phoneNumber')">确认</button>
	<br>
	<h3>
		WebService返回结果：<span id="status_phoneNumber"></span>
	</h3>
</body>
</html>
