<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.a{
	color:red;
}
.b{
	margin-left:100px;
	margin-right:20px;
}
.input{
	margin:20px 265px;
}
</style>
</head>
<body>
	<h1>请您修改<label class="a">${name }</label></h1>
	<hr>
	<form action="<%=request.getContextPath() %>/Update" method="get">
		<input type="hidden" name="fid" value=${fid } /><br/>
		<input type="hidden" name="id" value=${id } /><br/>
		<label class="b">请输入修改后的地区</label><input type="text" name="name"/><br/>${error }
		<input class="input" type="submit" value="完成"/>
	</form>
</body>
</html>