if('${sessionScope.user.role }' == "admin"){
		jnkc.innerHTML='${sessionScope.user.name }'+',欢迎您!'+' '+new Date().toLocaleDateString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());
}else{
	jnkc.innerHTML='${sessionScope.user.areaname }'+',欢迎您!'+' '+new Date().toLocaleDateString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());
}