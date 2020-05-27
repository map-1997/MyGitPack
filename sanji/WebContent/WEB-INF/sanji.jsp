<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript">
function sanji(){
	var shengid = document.getElementById("sheng");
	
	$.get("/sanji/ChaXunSheng",function(data,status){
		
		var Area = $.parseJSON(data);
		
		for(var i = 0; i < Area.length; i++){
			var option = new Option(Area[i].name,Area[i].id);
			shengid.appendChild(option);
		}
	});
}

function c(){
	var shengid = document.getElementById("sheng");
	
	var cityid = document.getElementById("city");
	
	var id = shengid.options[shengid.selectedIndex].value;
	
	$.get("/sanji/ChaXunCity?id="+id,function(data,status){
		var Area1 = $.parseJSON(data);
		
		
		if(cityid.options[1] == null){
			for(var i = 0; i < Area1.length;i++){
				var val = i+1;
				var option = new Option(Area1[i].name,Area1[i].id);
				cityid.appendChild(option);
				
			}
		}else{
			for(var i = cityid.options.length; i > 0; i--){
				cityid.options[i] = null;
			}
			for(var i = 0; i < Area1.length;i++){
				var val = i+1;
				var option = new Option(Area1[i].name,Area1[i].id);
				cityid.appendChild(option);
			}
		}
	});
}

function are(){
	var shengid = document.getElementById("sheng");
	
	var cityid = document.getElementById("city");
	
	var areaid = document.getElementById("area");
	
	var cityId = cityid.options[cityid.selectedIndex].value;
	
	$.get("/sanji/ChaXunCity?id="+cityId,function(data,status){
		var Area2 = $.parseJSON(data);
		
		if(areaid.options[1] == null){
			for(var i = 0; i < Area2.length;i++){
				var val = i+1;
				var option = new Option(Area2[i].name,Area2[i].id);
				areaid.appendChild(option);
			}
		}else{
			for(var i = areaid.options.length; i > 0; i--){
				areaid.options[i] = null;
			}
			for(var i = 0; i < Area2.length;i++){
				var val = i+1;
				var option = new Option(Area2[i].name,Area2[i].id);
				areaid.appendChild(option);
			}
		}
	});
}

function aaa(){
	var input = document.getElementById("input");
	
	var text = input.value;
	
	$.get("/sanji/AddSheng?name="+text,function(data,status){
		if(data == "yes"){
			alert("添加成功,请刷新页面");
		}else if(data == "no"){
			alert("添加失败");
		}else{
			alert(data);
		}
	});
}


function ddd(){
	location.href = "/sanji/SelectAll";
}

</script>
<style type="text/css">
label{
	display:inline-block;
	width:40px;
	margin:5px 10px;
}
select{
	width:100px;
	border-radius: 5px;
}
.a{
	margin:80px 45px;
}
.div{
	margin:20px 100px;
}
.div1{
	display:inline-block;
	float:left;
	margin-left:10px;
}
.button{
	float:left;
	margin:100px 10px;
}
.button1{
	float:left;
	margin:100px  5px;
}
#input{
	float:left;
	margin-top:100px;
	margin-left:100px;
}
</style>
</head>
<body onload="sanji()" >
	<h1>欢迎你</h1>
	<hr>
	<div class="div">
		<div class="div1">
			<label>省份</label><select id="sheng" onchange="c()">
				<option>请选择</option>
			</select><br/>
		</div>
		
		<div class="div1">
			<label>城市</label><select id="city" onchange="are()">
				<option>请选择</option>
			</select><br/>
		</div>
		
		
		<div class="div1">
			<label>地区</label><select id="area">
				<option>请选择</option>
			</select><br/>
		</div>
		<br/><br/><br/><br/><br/><br/><br/><br/>
		<input type="text" placeholder="请输入汉字并以省字结尾" id="input"/><button class="button1" onclick="aaa()">添加省</button>
		<button class="button" onclick="ddd()">查询所有</button><br/>
	</div>
</body>
</html>