<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
td{
	width:100px;
}
table{
	margin:20px 100px;
}
a{
	text-decoration: none;
}
#input{
	margin-left:40px;
}
table{
	 border-collapse: collapse;
	 margin:10px auto;
}
td{
	border:1px solid gray;
	text-align:center;
	color:gray;
	width:150px;
	height:40px;
}
.form2{
	width:221px;
	margin:0 auto;
	margin-top:50px
}
</style>
<script type="text/javascript">
function a(page){
	var pageId = document.getElementById("pageId");
	
	pageId.value = page;
	
	document.forms[0].submit();
	
}
</script>
</head>
<body>
	<h1>地区表</h1>
	<hr>
	<form class="form2" action="<%=request.getContextPath() %>/SelectAll" method="post">
		<input type="hidden" id="pageId" name="nowPage" value=""/>
		<input type="text" name="name" value=""/>
		<input type="button" onclick="a(1)" value="查询"/>
	</form>
	<form action="<%=request.getContextPath() %>/Delete" method="get">
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
					<a href="<%=request.getContextPath()%>/SelectAll?nowPage=${p.pPage }&name=${p.name } ">上一页</a>
					<a href="<%=request.getContextPath()%>/SelectAll?nowPage=${p.nPage }&name=${p.name } ">下一页</a>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input class="submit" type="submit" value="删除所有选择"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>