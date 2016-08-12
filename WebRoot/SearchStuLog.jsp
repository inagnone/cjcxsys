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
  		 	<table style="font-size: 12; border-collapse:   separate;   border-spacing:   5px;">
	  			<tbody>
	  				<tr>
	  					<td>
	  						<span>操作者:</span>
		    				<input id="user" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc;width:200px">
	  					</td>
	  					<td>
	  						<span>成绩对象:</span>
		    				<input id="cjname" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
	  					</td>
	  					<td>
							<span>操作:</span>
							<select id="action" name="action"  panelHeight="auto" style="width:100px">
								<option value="">全部</option>
								<option value="添加">新增</option>
								<option value="删除">删除</option>	
								<option value="更新">修改</option>
							</select>
						</td>
						<td>
	  						<span>ip地址:</span>
		    				<input id="ip" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
	  					</td>						
					</tr>
					<tr>
						<td colspan="2">
							<span>操作时间</span>
							<input id="st" class="easyui-datebox" name="st" data-options="formatter:myformatter,parser:myparser">至
							<input id="et" class="easyui-datebox" name="et" data-options="formatter:myformatter,parser:myparser">
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
						url:'servlet/GetStuActionLogServlet',
						autoRowHeight:true,
						fitColumns:true,
						remoteSort:false,
						">
	    			<thead>
	    				<tr>
	    					<th field="user" width="30" sortable="true">操作者</th>
	    					<th field="cjname" width="30" sortable="true">成绩对象</th>
			    			<th field="action" width="30" sortable="true" >操作</th>
			    			<th field="createdat" width="90" >操作日期</th>
			    			<th field="ip" width="30" sortable="true">操作者ip</th>
	    				</tr>
	    			</thead>
    			</table>
		</div>
</div>

<script src="js/data-grid.js"></script>
<script src="js/helloword.js"></script>
<script src="js/funtion.js"></script>


<script type="text/javascript">
//获取搜索条件
function doSearch(){
	$('#dg').datagrid('load',{  
		search: true,
		user: $('#user').val(),
		cjname: $('#cjname').val(),
		action: $('#action').val(),
		st: $('#st').val(),
		et: $('#et').val(),
		ip: $('#ip').val()
	});
}
</script>
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
  </body>
</html>
