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
	  		<div> 
		  		<div>
		  		 		<a href="ExportExcel" class="easyui-linkbutton" iconCls="icon-save" plain="true" >导出所有查询结果到excel文件</a>
		  		 		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="getstu()">导出选中结果到excel文件</a>	
		  		 		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加</a>	
		  		 		<a class="easyui-linkbutton" iconCls="icon-edit"  plain="true" onclick="edit()">修改</a>
		  		 		<a class="easyui-linkbutton" iconCls="icon-cancel"  plain="true" onclick="deletes()">删除</a>
	  		 		<hr>
	  		 	</div>	
  		 	</div>
  		 	
  		 	<table style="font-size: 12; border-collapse:   separate;   border-spacing:   5px;">
	  			<tbody>
	  				<tr>
	  					<td>
	  						<span>考试类别:</span>
							<select id="typeid" name="typeid"  panelHeight="auto" style="width:200px">
								<option value="11">全国水利安全生产标准化考试</option>
							</select>
	  					</td>
	  				</tr>
	  				<tr>
	  					<td>
	  						<span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span>
		    				<input id="name" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc;width:200px">
	  					</td>
	  					<td>
	  						<span>身份证号:</span>
		    				<input id="personid" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
	  					</td>
	  				</tr>
	  				<tr>
	  					<td>
	  						<span>验证码:</span>
	  						<input id="validcode" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc;width:200px">
	  					</td>	
	  					<td>
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
						url:'GetStuServlet',
						autoRowHeight:true,
						fitColumns:true,
						remoteSort:false,
						">
	    			<thead>
	    				<tr>
	    					<th data-options="field:'ck',checkbox:true"></th>
	    					<th field="id" width="50" sortable="true" hidden>id</th>
			    			<th field="name" width="30" sortable="true" >姓名</th>
			    			<th field="personid" width="90" >身份证号</th>
			    			<th field="company" width="100" sortable="true" >工作单位</th>
			    			<th field="examname" width="70" sortable="true">考试名称</th>
			    			<th field="exampc" width="50" sortable="true">考试批次</th>
			    			<th field="examtime" width="50" sortable="true">考试时间</th>
			    			<th field="sgqycj" width="80" sortable="true">施工企业成绩</th>
			    			<th field="sgdwcj" width="80" sortable="true">水管单位成绩</th>
			    			<th field="xmfrcj" width="80" sortable="true">项目法人成绩</th>
			    			<th field="zynlcj" width="80" sortable="true">专业能力成绩</th>
	    				</tr>
	    			</thead>
    			</table>
		</div>
</div>



<script src="js/data-grid.js"></script>
<script src="js/helloword.js"></script>
<script src="js/funtion.js"></script>

<div id="addstu" class="easyui-window" title="添加类型" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:700px;height:300px;padding:10px;">
	<form id="form">
		<input type="hidden" name="id">
		<table style="border-collapse:   separate; border-spacing:   10px;">
			<tr>
				<td>
					姓名：<input value="123" id="newname" name="name" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
				</td>
				<td>
					身份证号：<input id="newpersonid" name="personid" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
				</td>
			</tr>
			<tr>
				<td>
					工作单位：<input id="newcompany" name="company" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
				</td>
				<td>
					考试名称：<select name="examtype" id="newexamname"></select>
				</td>
			</tr>
			<tr>
				<td>
					考试批次：<input id="newexampc" name="exampc" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc">
				</td>
				<td>
					考试时间：<input id="newexamtime" class="easyui-datebox" type="text" name="examtime" data-options="formatter:myformatter,parser:myparser"></input>
				</td>
			</tr>
			<tr>
				<td>
					施工企业成绩：<input id="sgqycj" class="easyui-numberbox" name="sgqycj" precision="0" ></input>
				</td>
				<td>
					水管单位成绩：<input id="newsgdwcj" class="easyui-numberbox" name="sgdwcj" precision="0" ></input>
				</td>
			</tr>
			<tr>
				<td>
					项目法人成绩：<input id="newxmfrcj" class="easyui-numberbox" name="xmfrcj" precision="0" ></input>
				</td>
				<td>
					专业能力成绩：<input id="newzynlcj" class="easyui-numberbox" name="zynlcj" precision="0"></input>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input id="submit" type="submit" value="提交" class="easyui-linkbutton">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm('#form')">重置</a>
				</td>
			</tr>
		</table>
	</form>
	<form action="">
		<table width="100%">
			<tr>
				<td>
					导入excel文件：
					<input class="easyui-filebox" name="file1" data-options="prompt:'Choose a file...'" style="width:100%">
				</td>
			</tr>
			<tr><td><a href="#" class="easyui-linkbutton" style="width:100%">Upload</a></td></tr>
		</table>
	</form>
</div>

<script type="text/javascript">
	//添加
	function add(){
		clearForm('#form');
	   $.ajax({
		   type: "POST",
		   url: "GetExamTypeServlet",
		   success: function(types){
		     $("#newexamname  option").remove();
		     var typearray = eval(types);
		     $.each(types,function(i,item){
		     	 $("<option></option>")
                    .val(item["typeid"])
                    .text(item["examname"])
                    .appendTo($("#newexamname"));
		     });
		    $('#form').attr('action','AddStuServlet');
			$('#addstu').window('open');
		   }
	   });
	};
	
	function edit(){
		var stus = getselections();
		if(stus.length != 1){
			alert("请指定一行进行修改");
			return;
		}else{
			$('#form').form('load',{
				id:stus[0].id,
				name:stus[0].name,
				personid:stus[0].personid,
				company:stus[0].company,
				exampc:stus[0].exampc,
				examtime:stus[0].examtime,
				sgqycj:stus[0].sgqycj,
				sgdwcj:stus[0].sgdwcj,
				xmfrcj:stus[0].xmfrcj,
				zynlcj:stus[0].zynlcj
			});
			$('#form').attr('action','UpdateStuServlet');
			jsSelectItemByValue(document.getElementById("newexamname"), stus[0].examtype);
			$('#addstu').window('open');
		}
	};
	
	function deletes(){
		var rows = getselections();
		var ids = [];
		for(var i=0; i<rows.length; i++){
			ids.push(rows[i].id);
		}
		if (rows){
			$.messager.confirm('Confirm','您确认删除证书吗?',function(r){
				if (r){
					$.post("DeleteStuServlet",{id:ids.join(',')},function(result){
						if (result.success){
							$('#dg').datagrid('reload');	// reload the user data
						} else {
							$.messager.show({	// show error message
								title: 'Error',
								msg: result.errorMsg
							});
						}
					},'json');
					$('#dg').datagrid('reload');
				}
			});
		}
	}
	
	function getstu(){
		var ids = [];
		var rows = getselections();
		for(var i=0; i<rows.length; i++){
			ids.push(rows[i].id);
		}
		if(ids.length == 0){
			alert("请选择证书");
		}else{
			post('ExportExcel',{id:ids});
		}
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
