
// depend on  layui.all.js

function resetPwd(userOradmin) {
	var oldPwd = $("#resetPwd_oldPwd").val();
	var newPwd = $("#resetPwd_newPwd").val();
	var newPwd_confrim = $("#resetPwd_newPwd_confirm").val();
	if (oldPwd && newPwd && newPwd_confrim) {
		if (newPwd != newPwd_confrim) {
			layui.layer.msg("密码不一致,请重新输入");
		} else {
			$.ajax({
				url : '/jf/puresport/pagesController/ResetPwd',
				type : 'POST', // GET
				async : true, // 或false,是否异步
				data : {
					// userType:app.userType,
					oldPwd : oldPwd,
					newPwd : newPwd,
					userOradmin : userOradmin
				},
				timeout : 5000, // 超时时间
				dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
				beforeSend : function(xhr) {
					// console.log(xhr)
					console.log('发送前')
				},
				success : function(data, textStatus, jqXHR) {
					layui.layer.confirm('密码重置成功！请重新登录！', {
						icon : 3,
						title : '提示'
					}, function(index) {
						window.location = cxt
								+ '/jf/puresport/pagesController/loginout';
						layer.close(index);
					});
				},
				error : function(xhr, textStatus) {
					console.log('错误')
					console.log(xhr)
					console.log(textStatus)
				}
			})
		}
	} else {
		layui.layer.msg( "请补全信息!");
	}
}// 重置密码