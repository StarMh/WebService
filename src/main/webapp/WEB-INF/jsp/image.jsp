<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="resources/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	function send(name) {
		$.ajax({
			type : 'post',
			url : name,
			dataType : 'text',
			success : function(data) {
				$("#status" + "_" + name).html(data);
				$('#img'+ "_" + name).attr("src", "images/" + data + ".jpg");
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
	<h1>调用Web Service生成随机验证码图片</h1>
	<button onclick="send('getImage')">生成</button>
	<br>
	<h3>
		<span id="status_getImage"></span>
	</h3>
	<img id="img_getImage"/>
</body>
</html>
