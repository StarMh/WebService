<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>用户信息查询</title>
</head>
<body>

	<h2>Web Service服务返回的User对象</h2>
	<table>
		<tr>
			<td>名称：</td>
			<td>${name}</td>
		</tr>
		<tr>
			<td>性别：</td>
			<td>${gender}</td>
		</tr>
		<tr>
			<td>年龄：</td>
			<td>${age}</td>
		</tr>
	</table>
</body>
</html>