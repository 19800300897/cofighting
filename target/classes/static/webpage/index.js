function changeInfo(){
	var username = document.getElementById('username').value
	//console.log("用户名："+username)
	var password = document.getElementById('password').value
	var confirmPassword = document.getElementById('confirmPassword').value
	if(!username||!password||!confirmPassword){
		alert('请检查必填项！')
	}
	if(password != confirmPassword) {
		alert('两次输入密码不相同！')
	}
	var name = document.getElementById('name').value
	var QQ = document.getElementById('QQ').value
	var wechat = document.getElementById('weChat').value
	var phone = document.getElementById('phone').value
	
	var xhr = null;
	if(window.XMLHttpRequest){
		xhr=new XMLHttpRequest();
	}else{
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "http://localhost:8080/testGet?"
	xhr.open('get',url,true)
	xhr.send('username='+username+'&password='+password+
	'&name='+name+'&QQ='+QQ+'&wechat='+wechat+'&phone='+phone)
}