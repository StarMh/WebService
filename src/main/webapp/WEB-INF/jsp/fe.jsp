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
	<h1>调用WebService生成斐波那契数列</h1>
	<h3>生成前n位（n>0且n<=30)，输入n的值：</h3>
	<textarea id="message_getFe"></textarea>
	<button onclick="send('getFe')">确认</button>
	<br>
	<h3>
		WebService返回数组：<span id="status_getFe"></span>
	</h3>
</body>
</html>
