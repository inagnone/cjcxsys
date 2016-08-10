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
  	<script src="js/data-grid.js"></script>
	<script src="js/helloword.js"></script>
	<script src="js/funtion.js"></script>
  </head>
  
  <body>		
	<div class="content" style="height: 100%;">
		<div class="content-color-top">
	    	<div class="brand" ><img src="img/search.png" width="135" height="50" >
			    <c:if test="${sessionScope.username != null }">
					<h2 class="time" id="jnkc" style="position: absolute;top: 15px;margin-left: 300px;color: white;"></h2>
			    </c:if>
	    	</div>
		</div>
		<!-- 查询参数文本框 -->
	  	<div id="toolbar" style="padding:3px">
	  		<div> 
		  		<div>
		  		 		<c:if test="${sessionScope.username != null }">
		  		 			<a href="Servlet/ExportExcel" class="easyui-linkbutton" iconCls="icon-save" plain="true" >导出所有查询结果到excel文件</a>
		  		 			<a class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="getstu()">导出选中结果到excel文件</a>	
			  		 		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加</a>	
			  		 		<a class="easyui-linkbutton" iconCls="icon-edit"  plain="true" onclick="edit()">修改</a>
			  		 		<a class="easyui-linkbutton" iconCls="icon-cancel"  plain="true" onclick="deletes()">删除</a>
	  		 				<hr>
	  		 			</c:if>
	  		 	</div>	
  		 	</div>
  		 	
  		 	<table style="font-size: 12; border-collapse:   separate;   border-spacing:   5px;">
	  			<tbody>
	  				<tr>
	  					<td>
	  						<span>考试类别:</span>
							<select id="examtype" name="examtype"  panelHeight="auto" style="width:200px">
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
	  					<c:if test="${sessionScope.username != null }">
		  					<td>
		  						<a  class="easyui-linkbutton" plain="true" onclick="doSearch2()" iconCls="icon-search">查询</a>
		  					</td>
		  				</c:if>
	  				</tr>
	  				<tr>
	  					<c:if test="${sessionScope.username == null }">
		  					<td>
		  						<span>验证码:</span>
		  						<input id="validcode" class="easyui-textbox" style="line-height:26px;border:1px solid #ccc;width:200px">
		  					</td>	
		  					<td>
		  						<img id="valid" src="AuthImage" onclick="changeImg(this)" style="cursor: pointer;"/>
		  					</td>
		  					<td>
		  						<a  class="easyui-linkbutton" plain="true" onclick="doSearch()" iconCls="icon-search">查询</a>
		  					</td>
		  				</c:if>
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

<div id="addstu" class="easyui-window" title="添加类型" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:750px;height:500px;padding:10px;">
	<form id="form" class="easyui-form" data-options="novalidate:false">
		<input type="hidden" name="id">
		<table style="border-collapse:   separate; border-spacing:   10px;">
			<tr>
				<td>
					姓名：<input value="123" id="newname" name="name" class="easyui-textbox" data-options="required:true,missingMessage:'用户名不能为空'" style="line-height:26px;border:1px solid #ccc">
				</td>
				<td>
					身份证号：<input id="newpersonid" name="personid" class="easyui-textbox" data-options="required:true,missingMessage:'身份证不能为空'" style="line-height:26px;border:1px solid #ccc">
				</td>
			</tr>
			<tr>
				<td>
					工作单位：<input id="newcompany" name="company" class="easyui-textbox" data-options="required:true,missingMessage:'工作单位不能为空'" style="line-height:26px;border:1px solid #ccc">
				</td>
				<td>
					考试名称：<select name="examtype" data-options="required:true" id="newexamname"></select>
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
					<a id="submit" href="javascript:void(0)" class="easyui-linkbutton" onclick="submitform()">提交</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm('#form')">重置</a>
				</td>
			</tr>
		</table>
	</form>
	模板选择：
	<form action="servlet/DownloadServlet">
		<select id="cc" class="easyui-combobox" name="templetfile" style="width:200px;">
			<option value="WEB-INF/templet/templet.xls">模板1</option>
		</select>
		<input type="submit" value="下载">
	</form>
	<form action="Servlet/LoadExcel" enctype="multipart/form-data" method="post">
		<table width="100%">
			<tr>
				<td>
					导入excel文件：
					<input class="easyui-filebox" name="file1" data-options="prompt:'选择一个文件'" style="width:100%">
				</td>
			</tr>
			<tr><td><input type="submit" value="上传" class="easyui-linkbutton" style="width:100%"></td></tr>
		</table>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	 $.ajax({
		   type: "POST",
		   url: "Servlet/GetExamTypeServlet",
		   success: function(types){
		     $("#newexamname  option").remove();
		     $.each(types,function(i,item){
		     	 $("<option></option>")
                   .val(item["typeid"])
                   .text(item["examname"])
                   .appendTo($("#examtype"));
		     });
		   }
	   });
});
	//提交表单
	function submitform(){
		$('#form').form('submit',{
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(){
				$('#addstu').window('close');
				$('#dg').datagrid('reload');
			}
		});
	}
	//添加
	function add(){
		clearForm('#form');
	   $.ajax({
		   type: "POST",
		   url: "Servlet/GetExamTypeServlet",
		   success: function(types){
		     $("#newexamname  option").remove();
		     $.each(types,function(i,item){
		     	 $("<option></option>")
                    .val(item["typeid"])
                    .text(item["examname"])
                    .appendTo($("#newexamname"));
		     });
		    $('#form').attr('action','Servlet/AddStu');
			$('#addstu').window('open');
		   }
	   });
	};
	
	function edit(){
		clearForm('#form');
		   $.ajax({
			   type: "POST",
			   url: "Servlet/GetExamTypeServlet",
			   success: function(types){
			     $("#newexamname  option").remove();
			     $.each(types,function(i,item){
			     	 $("<option></option>")
	                    .val(item["typeid"])
	                    .text(item["examname"])
	                    .appendTo($("#newexamname"));
			     });
			   }
		   });
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
			$('#form').attr('action','Servlet/UpdateStu');
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
					$.post("Servlet/DeleteStu",{id:ids.join(',')},function(result){
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
	};
	
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
	};
	
	function changeImg(img){
		img.src = img.src+"?time="+new Date().getTime();
  	};
	
	//执行搜索
	function doSearch(){
		 $.ajax({
			   type: "POST",
			   url: "ValidCodeServlet",
			   data: "validcode="+$('#validcode').val()+"&name="+$('#name').val()+"&personid="+$('#personid').val(),
			   success: function(result){
			     if(result.success){
					//获取搜索条件
			    	$('#dg').datagrid('load',{  
				   		search: true,
				    	name: $('#name').val(),
				    	personid: $('#personid').val(),
			   		});
			     }else{
			    	 $.messager.alert('My Title',result.msg,'info');
			     }
			     changeImg(document.getElementById("valid"));
			   }
		   });
	};
	
	function doSearch2(){
		//获取搜索条件
    	$('#dg').datagrid('load',{  
	   		search: true,
	    	name: $('#name').val(),
	    	personid: $('#personid').val(),
	    	user:'admin'
   		});
	}
</script>
<!-- 信息提示窗口 -->
<c:if test="${requestScope.msg != null }">
	<script>	
		$.messager.alert('My Title','${requestScope.msg}','info');
	</script>
</c:if>
<c:if test="${param.msg != null }">
	<script>	
		$.messager.alert('My Title','${param.msg}','info');
	</script>
</c:if>

  </body>

</html>
