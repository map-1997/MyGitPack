<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
label{
	display:inline-block;
	width:150px;
	text-align:center;
	margin:10px 10px;
}
.form1{
	margin:50px 150px;
}
.submit1{
	margin:20px 155px;	
}
</style>
</head>
<body>
	<h1>用户登录</h1>
	<hr>
	<form class="form1" action="<%=request.getContextPath() %>/Login" method="post">
		<label>请输入用户名:</label><input type="text" name="username" /><label style="color:red">${nameError }</label><br/>
		<label>请输入密码:</label><input type="password" name="pass" /><label style="color:red">${passError }</label><br/>
		<input class="submit1" type="submit" value="登录" />
	</form>
</body>
</html>