// 依赖于commonUtil jquery layer

function setValPhoneBtn(divBtnSelector, divInputSelector, type, module){
	
	var btnDom = $(divBtnSelector);
	var inputDom = $(divInputSelector);
	if(!btnDom || !inputDom){
		console.log("setValPhoneBtn !btnDom || !inputDom");
		return;
	}
	btnDom.on('click', function() {
		var phone = inputDom.val();
		if (!validatePhone(phone)) {
			layer.msg('手机号码格式不正确，请确认！');
			return;
		}

		btnSetDisable(btnDom, true, "正在发送...");
		ajaxSendAuthCode(1, 'sendPhoneCode', {
			phone : phone,
			type: type,
			module:module
		}, '验证码已发送到您手机,请注意查收', '验证码发送失败，请重试或联系管理员', btnDom);
		btnSetDisable(btnDom, false, "点击获取");

	});
}

function setValEmailBtn(divBtnSelector, divInputSelector, type, module){
	
	var btnDom = $(divBtnSelector);
	var inputDom = $(divInputSelector);
	if(!btnDom || !inputDom){
		console.log("setValEmailBtn !btnDom || !inputDom");
		return;
	}
	
	btnDom.on('click', function() {
		var email = inputDom.val();
		if (!validateEmail(email)) {
			layer.msg('邮箱格式不正确，请确认！');
			return;
		}
		
		btnSetDisable(btnDom, true, "正在发送...");
		ajaxSendAuthCode(2, 'sendEmailCode', {
			email : email,
			type: type,
			module:module
		}, '验证码已发送到您邮箱,请注意查收', '验证码发送失败，请重试或联系管理员', btnDom);
		btnSetDisable(btnDom, false, "点击获取");

	});
}


// ======== ajax
var countdownPhone = 60;
var countdownEmail = 60;

function setTime(flag, obj) {
	var countdown = flag === 1 ? countdownPhone : countdownEmail;

	if (countdown == 0) {
		btnSetDisable(obj, false, "点击获取");
		if (flag === 1) {
			countdownPhone = 60;
		} else {
			countdownEmail = 60;
		}
		return;
	} else {
		btnSetDisable(obj, true, "剩余" + countdown + "秒");
		if (flag === 1) {
			countdownPhone--;
		} else {
			countdownEmail--;
		}
	}
	setTimeout(function() {
		setTime(flag, obj)
	}, 1000);
}

function ajaxSendAuthCode(flag, addr, dataObj, tipSuc, tipFail, domObj) {
	$.ajax({
		url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/" + addr)),
		data : dataObj,
		dataType : "json",
		type : "post",
		timeout : 5000,
		async : false,
		cache : false,
		success : function(res) {
			if (res.hasSuc) {
				layer.msg(tipSuc);
				setTime(flag, domObj);
			} else {
				layer.msg(res.tipStrings.join("  "));
			}
		},
		error : function() {
			layer.msg(tipFail);
		}
	})
}
