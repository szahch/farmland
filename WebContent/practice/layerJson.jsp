<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript" src="/Farmland/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">  
     function test(){  
         var name=document.getElementById("name").value;          
         $.ajax({   
                type:"GET", //请求方式  
                url:"http://192.168.1.244:8080/arcgis/rest/services/Learning/GeoImage/MapServer/layers?f=pjson", //请求路径  
                cache: false,     
                data:"name="+name,  //传参  
                dataType: 'json',   //返回值类型  
               success:function(msg){
            	   
            	   console.log(msg);
            	   
                     var me=msg[0];   
                    var me=msg[0].userName;  
                   for(i in msg){  
                       var me1=msg[i];  
                   }  
                      
                   },  
                error:function(){  
                    alert("error");  
                }  
                });  
      }  
</script>
</head>
<body>
	<form action="" method="post">
		<input type="text" name="name" id="name" value="nameme"> <input
			type="button" name="b" value="测试" onclick=test()>
	</form>
</body>
</html>