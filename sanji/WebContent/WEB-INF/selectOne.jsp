<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
function aa(){
	
	var input = $("#input").val();
	
	var id  = ${id };
	
	$.get("/sanji/AddOther?name="+input+"&id="+ id ,function(data,status){
		if(data == "yes"){
			alert("添加成功,请刷新页面");
		}else if(data == "no"){
			alert("添加失败");
		}else{
			alert(data);
		}
	});
}
</script>
<style type="text/css">
table{
	 border-collapse: collapse;
	 margin:20px auto;
}
td{
	border:1px solid gray;
	text-align:center;
	color:gray;
	width:150px;
	height:40px;
}
a{
	text-decoration: none;
}
#input{
	margin-left:500px;
	margin-top:50px;
}
</style>

</head>
<body>
	<h1>该地区下级地区表</h1>
	<hr>
	<form action="<%=request.getContextPath() %>/Delete" method="get">
		<input type="text" id="input"/>
		<input onclick="aa()" type="button" value="添加地区"/>
		<input type="submit" value="删除所有选择"/>
		<table>
			<tr>
				<td>选择</td>
				<td>地区</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${p.user }" var="m">
				<tr>
					<td><input type="checkbox" name="checkbox" value="${m.id }"/></td>
					<td><a href="http://127.0.0.1:8080/sanji/ChaXunOne?id=${m.id}">${m.name }</a></td>
					<td><a href="http://127.0.0.1:8080/sanji/Delete?id=${m.id}">删除</a>&nbsp;&nbsp;&nbsp;<a href="http://127.0.0.1:8080/sanji/GoUpdate?name=${m.name }&id=${m.id }&fid=${m.fid }">修改</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3">
					总条数:${p.count }
					总页数:${p.pageCount }
					<a href="<%=request.getContextPath()%>/ChaXunOne?nowPage=${p.pPage }&id=${id }">上一页</a>
					<a href="<%=request.getContextPath()%>/ChaXunOne?nowPage=${p.nPage }&id=${id }">下一页</a>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>