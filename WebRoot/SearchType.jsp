<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <base href="<%=basePath%>">
    
    <title>全国水利安全远程教育培训平台证书查询系统</title>
    
	
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/icon.css">
	<link rel="stylesheet" type="text/css" href="css/app.min.css">
	<link rel="stylesheet" href="css/normalize.css">

    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />

  	<script type="text/javascript" src="easyui/js/jquery.min.js"></script>
  	<script type="text/javascript" src="easyui/js/jquery.easyui.min.js"></script>
  	<script type="text/javascript" src="easyui/js/datagrid-scrollview.js"></script>
  </head>
  
  <body>		
	<div class="content" style="height: 100%;">
		<div class="content-color-top">
	    	<div class="brand" ><img src="img/search.png" width="135" height="50" >
			    <c:if test="${sessionScope.user != null }">
					<h2 class="time" id="jnkc" style="position: absolute;top: 15px;margin-left: 300px;color: white;"></h2>
			    </c:if>
	    	</div>
		</div>
		<!-- 查询参数文本框 -->
	  	<div id="toolbar" style="padding:3px">
	  	<c:if test="${sessionScope.user != null }">
	  		<div> 
		  		<div>
		  		 		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$('#addtype').window('open')">添加</a>	
		  		 		<a class="easyui-linkbutton" iconCls="icon-edit"  plain="true" onclick="edit()">修改</a>
	  		 		<hr>
	  		 	</div>	
  		 	</div>
  		 </c:if>	
  		 	<table style="font-size: 12; border-collapse:   separate;   border-spacing:   5px;">
	  			<tbody>
	  				<tr>
	  					<td>
	  						<span>考试类型编号:</span>
		    				<input id="typeid" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc;width:200px">
	  					</td>
	  					<td>
	  						<span>考试名称:</span>
		    				<input id="typename" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
	  					</td>
	  					<td>
	  						<a  class="easyui-linkbutton" plain="true" onclick="doSearch()" iconCls="icon-search">搜索</a>
	  					</td>
	  				</tr>
	  			</tbody>
	  		</table>
	    </div>
	    
	    <div class="content-body" style="height: 90%">
        		<table id="dg" class="easyui-datagrid" style="width: 100%;height: 100%" toolbar="#toolbar" rownumbers="true" pagination="true" 
	        		data-options="
	        			rownumbers:true,
	        			sorter:function(a,b){return a.Name == b.Name ? 0 : a.Name > b.Name ? 1 : -1;},
						url:'GetExamTypeServlet',
						autoRowHeight:true,
						fitColumns:true,
						remoteSort:false,
						">
	    			<thead>
	    				<tr>
	    					<th data-options="field:'ck',checkbox:true"></th>
	    					<th field="id" width="50" sortable="true" hidden>id</th>
			    			<th field="typeid" width="30" sortable="true" >考试类别编号</th>
			    			<th field="examname" width="90" >考试名称</th>
	    				</tr>
	    			</thead>
    			</table>
		</div>
</div>

<script src="js/data-grid.js"></script>
<script src="js/helloword.js"></script>
<script src="js/funtion.js"></script>

<div id="addtype" class="easyui-window" title="添加类型" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:370px;height:150px;padding:10px;">
	<form action="addType" id="type" method="post">
		<table style="border-spacing:   10px;">
			<tr>
				<td>
					考试类型编码：
				</td>
				<td>
					<input name="typeid" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
				</td>
			</tr>
			<tr>
				<td>
					考试类型名称：
				</td>
				<td>
					<input name="examname" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="提交" class="easyui-linkbutton">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm('#type')">重置</a>
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="updatetype" class="easyui-window" title="添加类型" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:370px;height:150px;padding:10px;">
	<form action="UpdateTypeServlet" id="type">
		<input type="hidden" id="newtypeid" name="typeid" value="">
		<table style="border-collapse:   separate; border-spacing:   10px;">
			<tr>
				<td>
					考试类型名称：
				</td>
				<td>
					<input id="newexamname" name="examname" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="提交" class="easyui-linkbutton">
				</td>
			</tr>
		</table>
	</form>
</div>

<!-- 信息提示窗口 -->
<c:if test="${requestScope.msg != null }">
	<script>	
		var msg = '${requestScope.msg}';
		msg = decodeURI(msg);
		$.messager.alert('My Title','${requestScope.msg}','info');
	</script>
</c:if>
<c:if test="${param.msg != null }">
	<script>	
		var msg = '${param.msg != null }';
		msg = decodeURI(msg);
		$.messager.alert('My Title','${param.msg}','info');
	</script>
</c:if>
<script type="text/javascript">
function edit(){
	var ids = getselections();
	if(ids.length !=1){
		alert("请指定一种类型");
		return ;
	}
	$('#newtypeid').val(ids[0].typeid);
	$('#newexamname').val(ids[0].examname);
	$('#updatetype').window('open');
	
}
//执行搜索
function doSearch(){
	//获取搜索条件
   	$('#dg').datagrid('load',{  
   		search: true,
   		typeid: $('#typeid').val(),
   		typename: $('#typename').val(),
	});
}
</script>
  </body>
</html>
