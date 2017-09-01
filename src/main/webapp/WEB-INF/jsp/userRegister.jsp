<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>提交用户信息</title>
</head>
<body>
	<h2>用户信息封装成User对象作为参数，调用WebService服务</h2>
	<form:form method="POST" action="addUser">
		<table>
			<tr>
				<td><form:label path="name">名字：</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="gender">性别：</form:label></td>
				<td><form:radiobutton path="gender" value="M" label="男" /> <form:radiobutton
						path="gender" value="F" label="女" /></td>
			</tr>
			<tr>
				<td><form:label path="age">年龄（整数）：</form:label></td>
				<td><form:input path="age" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交表单" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>