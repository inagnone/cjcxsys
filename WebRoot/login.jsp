<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html style="height: 100%">
<head>
<script type="text/javascript">
	if(top != this){
		window.parent.location.reload();	
	}
</script>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath }/Certificate/css/app.min.css">
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath }/Certificate/js/function.js"></script>
<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>
   
</head>
<body style="height: 100% ">

<div class="header">
	<div class="header-body">
	    <div class="header-top">
	       <a href="http://slaqpx.hhu.edu.cn" target="_self"><img src="img/return.png"></img></a>&nbsp;&nbsp;
		  <a href="login.jsp"><img src="img/login1.png"></img></a>	      
		 </div>
		<div class="brand"><img src="img/logo.png" width="700" height="65">
		  
	  </div>
	</div>
	<div class="header-color-bottom"></div>
</div>


<c:if test="${requestScope.msg != null }">
	<div class="alert alert-danger" role="alert">${requestScope.msg}</div>
</c:if>



<div class="login-content" >
   <div class="login-box" >
       <div class="login-box-top">
	          <div ><img src="img/login.png" width="128" height="62"></div>
		</div>
		<br>
		<form class="form-horizontal" action="loginServlet" method="post">
		    
		    <div class="form-group" style="position: relative;left: 40px">
		        <label for="user" class="col-sm-2 control-label">用户名：</label>
		        <div class="col-sm-7">
		        	<input class="form-control" id="user"  id="user" type="text" name="username" placeholder="user">
		        </div>
		    </div>
		
		   <div class="form-group" style="position: relative;left: 40px">
		        <label for="password" class="col-sm-2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
		        <div class="col-sm-7">
		        	<input class="form-control" id="password" type="password" name="password" placeholder="password">
		        </div>
		   </div>
		    <div class="form-group" style="position: relative;left: 40px">
		        <label  for="validcode" class="col-sm-2 control-label">验证码：</label>
		        <div class="col-sm-3">
		       		<input class="form-control" id="password" name="validcode" placeholder="验证码">
		        </div>
			    <div >
			    	<img src="AuthImage" onclick="changeImg(this)" style="cursor: pointer;"/>
			    </div>
			
		    </div>
		    <div class="form-group" align="center" >
			    	<input class="btn btn-default" id="embed-submit" type="submit" value="登陆" >
			</div>
		</form>
	  
	   
   </div>
</div>

<div class="footer mt-10">
	<div class="footer-top"></div>
	<div class="footer-body pt-10">
		<div class="p-10">
			<p>
				<span class="ml-30">主管单位：<a href=" http://aqjd.mwr.gov.cn/" target="_blank">水利部安全监督司</a></span>
				<span class="ml-30">主办单位：<a href="http://www.cwec.org.cn/" target="_blank">中国水利企业协会</a></span>
				<span class="ml-30">承办单位：<a href="http://www.hhu.edu.cn" target="_blank">河海大学</a></span>
				<span class="ml-30">友情链接：
					<span class="footer-select">
					  <select id="FriendLink" class="footer_sel">
					    <option selected="" value="">友情链接</option>
					    <option value="http://www.mwr.gov.cn/">中华人民共和国水利部</option>
					    <option value="http://aqjd.mwr.gov.cn/">水利部安全监督司</option>
					    <option value="http://www.cwec.org.cn/">中国水利企业协会</option>
					    <option value="http://www.cahee.org.cn/">中国水利教育协会</option>
					    <option value="http://slpx.hhu.edu.cn">基层水利队伍培训工程</option>
					    <option value="http://www.hhu.edu.cn">河海大学</option>
					    <option value="http://jjy.hhu.edu.cn">河海大学继续教育学院</option>
					  </select>
					</span>
				</span>
			</p>

		</div>		
		<div class="pb-10">
			<p class="p-5">地址：江苏省南京市西康路1号 邮编：210098 办公室电话：025-83786362 技术咨询电话：025-83787318</p>
			<p class="p-5">版权： copyright 2014 河海大学远程与继续教育学院 All rights reserved</p>
		</div>	
	</div>
</div>
</body>
</html>
