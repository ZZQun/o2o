$(function(){
	var bindUrl = '/o2o/local/bindlocalauth';
	var usertype = getQueryString('usertype');
	function go(){
		if(usertype == 1){
			window.location.href = '/o2o/frontend/index';
		}else {
			window.location.href = '/o2o/shopadmin/shoplist';
		} 
	}
	$('#submit').click(function(){
		var userName = $('#username').val();
		var password = $('#psw').val();
		var verifyCodeActual = $('#j_captcha').val();
		var needVerify = false;
		if(!verifyCodeActual){
			$.toast('请输入验证码！');
			return;
		}
		$.ajax({
			url:bindUrl,
			async:false,
			cache:false,
			type:'POST',
			dataType:'json',
			data:{
				userName:userName,
				password:password,
				verifyCodeActual:verifyCodeActual
			},
			success:function(data){
				if(data.success){
					//0.5秒后跳转
					$.toast('绑定成功！');
					setTimeout(go,500);
				}else {
					$.toast('提交失败！' + data.errMsg);
					$('#captcha_img').click();
				}
			}
		});
	});
});