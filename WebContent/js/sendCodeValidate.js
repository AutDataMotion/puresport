

// 依赖于commonUtil jquery layui
$(function() {
	
	layui.use([ 'layer'], function() {
		var layer = layui.layer;
		
		layui.$('#btn-val-phone').on('click', function(){
			var phone = $('#mblph_no').val();
			console.log(phone);
			if(!validatePhone(phone)){
				layer.msg('手机号码格式不正确，请确认！');
				return ;
			}
			var btnValPhone = $('#btn-val-phone');
			ajaxSendAuthCode(1, 'sendPhoneCode', {phone: phone}, '验证码已发送到您手机,请注意查收', '验证码发送失败，请重试或联系管理员', btnValPhone);
		    
		});
		
		layui.$('#btn-val-email').on('click', function(){
			var email = $('#email').val();
			console.log(email);
			if(!validateEmail(email)){
				layer.msg('邮箱格式不正确，请确认！');
				return ;
			}
			var btnValEmail = $('#btn-val-email');
			ajaxSendAuthCode(2, 'sendEmailCode', {email: email}, '验证码已发送到您邮箱,请注意查收', '验证码发送失败，请重试或联系管理员', btnValEmail);
		    
		});
		
		// ======== ajax
		var countdownPhone = 60;
		var countdownEmail = 60;
		
        function setTime(flag, obj) {
        	var countdown = flag===1?countdownPhone:countdownEmail;
        	
            if (countdown == 0) {
                obj.prop('disabled', false);
                obj.text("点击获取");
                if(flag===1){
                	countdownPhone = 60;
                } else {
                	countdownEmail = 60;
                }
                return;
            } else {
                obj.prop('disabled', true);
                obj.text("剩余"+countdown+"秒") ;
                if(flag===1){
                	countdownPhone--;
                } else {
                	countdownEmail--;
                }
            }
            setTimeout(function() { setTime(flag, obj) },1000);
        }

		function ajaxSendAuthCode(flag, addr, dataObj, tipSuc, tipFail, domObj){
			$.ajax({
                url:encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/"+addr)),
                data:dataObj,
                dataType:"json",
                type:"post",
                timeout:5000,   
                async : false,
                cache : false,
                success:function(res){
                	console.log('sendRes', res)
                    if(res.hasSuc){
                        layer.msg(tipSuc);
                        setTime(flag, domObj);
                    }else{
                    	layer.msg(tipFail);
                    }
                },
                error:function(){
                	layer.msg(tipFail);
                }
            })
		}
		
		
	});
});