/**
 * author:lyf
 * 
 */

var app = {};


function loginAlert(msg) {
	$('#myModallyf_content').text(msg);
	$('#myModallyf').modal('show');
}

var userImproveInitData = null;
var login_needValSptPrj = false;
var login_needValEmail = false;
var login_needValPhone = false;
var login_needValDepart = false;

function userImproveFormExchange(data){
	
	if (data.needValPhone) {
		$('#divValPhone').show();
	}
	
	if (data.needValNmChar) {
		$('#divNmChar').show();
	}
	
//	if (data.needValEmail) {
//		login_needValEmail = true;
//		$('#divValEmail').show();
//   }

	$('#divValSport').hide();
	$('#divDepart').hide();
	$('#divPost').hide();
	
	if(data.userType=='运动员'){
		if (data.needValSptPrj) {
			$('#divValSport').show();
		}
		
	} else {
		// 辅助人员的
		if(data.belongToInstitute){
			// 属于协会 可能验证运动项目
			if (data.needValSptPrj) {
				$('#divValSport').show();
			}
		}
		if (data.needValDepart) {
			$('#divDepart').show();
			$('#divPost').show();
		}
	}
	
//	if (data.belongToInstitute) {
//	app.belongToInstitute = data.belongToInstitute;
//	$("#getSportItem_user_assist").toggle();
//}
	
}

function user_login() {

	var account = $("#form-phone_user").val().trim();
	var pwd = $("#form-pwd_user").val().trim();
	var authCode = $("#authCode_user").val().trim();
	if (!account || !pwd || !authCode) {
		loginAlert('有空值，请检查！');
		return;
	}

	// if(!validatePhone(account) && !validateEmail(account) &&
	// !validateID(account)){
	if (!validateID(account)) {
		loginAlert('账号格式不正确，请检查！');
		return;
	}

	$.ajax({
		url : '/jf/puresport/t1usrBsc/login',
		type : 'POST', // GET
		async : true, // 或false,是否异步
		data : {
			account : account,
			pwd : pwd,
			authCode : authCode
		},
		timeout : 5000, // 超时时间
		dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
		beforeSend : function(xhr) {
		},
		success : function(data, textStatus, jqXHR) {
			
			if (data.flag) {
				
				app.userType = data.userType;
				if (data.needImproveInfoOrNot) {
					
					$("#userLoginPanel_1").toggle();
					$("#userLoginPanel_2").toggle();
					
					// header的下拉框调整
					$("#divDropMenu-login").hide();
					$("#divDropMenu-regist").hide();
					$("#divDropMenu-center").show();
					$("#divDropMenu-logout").show();

					userImproveInitData = data;
					
					if(app.userType=='运动员' || app.userType=='辅助人员' ){
						$('#selectUserType').val(app.userType);
					} else {
						$('#selectUserType').val('请选择人员类型');
					}
					userImproveFormExchange(userImproveInitData);

				} else {
					window.location = data.url;
				}

			} else {
				loginAlert(data.msg);
			}
		},
		error : function(xhr, textStatus) {
			console.log('错误', xhr, textStatus);
		}
	})

}

function user_ImproveType(user_type) {
	
	app.userType = user_type;
	userImproveInitData.userType = app.userType;
	userImproveFormExchange(userImproveInitData);
}

var admin_needValInstitute = false;
var admin_needValEmail = false;
var admin_needValPhone = false;

function admin_login() {

	var account = $("#form-phone_admin").val().trim();
	var pwd = $("#form-pwd_admin").val().trim();
	var authCode = $("#authCode_admin").val().trim();
	if (!account || !pwd || !authCode) {
		loginAlert('有空值，请检查！');
		return;
	}

	// if(!validatePhone(account) && !validateEmail(account) && !validateID(account)){
	if (!validateID(account)) {
		loginAlert('账号格式不正确，请检查！');
		return;
	}
	
	$.ajax({
				url : '/jf/puresport/t6MgrAhr/login',
				type : 'POST', // GET
				async : true, // 或false,是否异步
				cache : false,
				data : {
					account : account,
					pwd : pwd,
					authCode : authCode
				},
				timeout : 5000, // 超时时间
				dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
				beforeSend : function(xhr) {
					console.log('发送前')
				},
				success : function(data, textStatus, jqXHR) {
					console.log(data);
					if (data.flag) {
						if (data.needImproveInfo) {
							$("#adminLoginPanel_1").toggle();
							$("#adminLoginPanel_2").toggle();
							
							if (data.needValInstitute) {
								admin_needValInstitute = true;
								$('#getxiehuiItem').show();
							}
							
							if (data.needValPhone) {
								admin_needValPhone = true;
								$('#divValPhone_admin').show();
							}
							if (data.needValEmail) {
								admin_needValEmail = true;
								$('#divValEmail_admin').show();
							}
							
						} else {
							var urlRedirect = data.url + "?r=" + Math.random();
							console.log('redirect', urlRedirect);
							window.location = urlRedirect;
						}

					} else {
						loginAlert(data.msg);
					}
				},
				error : function(xhr, textStatus) {
					console.log('错误', xhr, textStatus)
				}
			})

}

$(function(){
	
	// user 
	setValPhoneBtn('#btn-val-phone', '#mblph_no');
	setValEmailBtn('#btn-val-email', '#email');

	// admin
	setValPhoneBtn('#btn-val-phone_admin', '#mblph_no_admin', "a");
	setValEmailBtn('#btn-val-email_admin', '#email_admin', "a");
	
});

function Improve_user_info() {
	
	if(!app.userType ||  app.userType == "请选择人员类型"){
		layui.layer.msg("请选择一种人员类型！");
		return ;
	}
	var nmChar = $('#form-nm_char').val().trim();
	if(userImproveInitData.needValNmChar){
		if(!nmChar){
			layui.layer.msg("姓名拼音不能为空！");
			return ;
		}
		var tokens = nmChar.trim().split(" ");
		if(tokens.length == 2){
		  if((!/^[a-zA-Z]+$/.test(tokens[0])) || (!/^[a-zA-Z]+$/.test(tokens[1]))){
			  layui.layer.msg("姓名拼音都需要为字母");
			  return ;
		  }
		}else {
			layui.layer.msg("姓和名的拼音 中间需有一个空格，名的拼音无空格");
			return ;
		}
	}
	
	var phone = $('#mblph_no').val().trim();
	var mblphValCode = $('#mblphValCode').val().trim();
	
	if(userImproveInitData.needValPhone){
		if(!phone || !mblphValCode){
			layui.layer.msg("手机号及其验证码不能为空！");
			return ;
		}
		if(!validatePhone(phone)){
			layui.layer.msg("手机号码格式不正确！");
			return ;
		}
	}
	
//	var email = $('#email').val().trim();
//	var emailValCode = $('#emailValCode').val().trim();
//	
//	if(userImproveInitData.needValEmail){
//		if(!email || !emailValCode){
//			layui.layer.msg("邮箱及其验证码不能为空！");
//			return ;
//		}
//		if(!validateEmail(email)){
//			layui.layer.msg("邮箱格式不正确！");
//			return ;
//		}
//	}
	
	var sptPrj = $('#spt_prj_sporter option:selected').val().trim();
	
	var sendData = {};
	if (app.userType == "运动员")// 运动员
	{
		
		if (userImproveInitData.needValSptPrj) {
			if (sptPrj == "请选择项目") {
				layui.layer.msg("请选择项目!");
				return;
			}
		}
		
		sendData = {
			usertype : app.userType,
			phone : phone,
			mblphValCode : mblphValCode,
			sptPrj:sptPrj,
			nmChar:nmChar,
		};

	} else {// 辅助人员
		
		if(userImproveInitData.belongToInstitute){
			// 属于协会 可能验证运动项目
			if (userImproveInitData.needValSptPrj) {
				if (sptPrj == "请选择项目") {
					layui.layer.msg("请选择项目!");
					return;
				}
			}
		}
		
		var company = $('#form-company_assist').val().trim();
		var position = $('#form-position_assist').val().trim();
		
		if (userImproveInitData.needValDepart) {
			if(!company || !position){
				layui.layer.msg("工作单位或职称不能为空！");
				return ;
			}
		}
		
		sendData = {
				usertype : app.userType,
				phone : phone,
				mblphValCode : mblphValCode,
				sptPrj:sptPrj,
				company : company,
				position : position,
				nmChar:nmChar,
			};
	}
	
	$.ajax({
		url : '/jf/puresport/t1usrBsc/ImproveUserInfo',
		type : 'POST', // GET
		async : true, // 或false,是否异步
		data : sendData,
		timeout : 5000, // 超时时间
		dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
		beforeSend : function(xhr) {

			console.log('发送前')
		},
		success : function(data, textStatus, jqXHR) {

			if (data.flag) {
				window.location = data.url;
			} else {
				layui.layer.msg( data.msg);
			}
		},
		error : function(xhr, textStatus) {
			console.log('错误');
		},
		complete: function(){
		}
	})

}
function Improve_user_info_selfcenter(usr_tp) {
	app.userType = usr_tp;
	Improve_user_info();
}

function Improve_admin_info() {

	var xiehuiItemName = $('#getxiehuiItemName option:selected').val();

	if (admin_needValInstitute) {
		if (!xiehuiItemName || xiehuiItemName == "请选择协会项目") {
			Tips('myModallyf_content_admin', "请选择协会项目");
			return;
		}
	}
	
	var phone = $('#mblph_no_admin').val().trim();
	var mblphValCode = $('#mblphValCode_admin').val().trim();
	
	var email = $('#email_admin').val().trim();
	// var emailValCode = $('#emailValCode_admin').val().trim();
	
	if(admin_needValEmail){
//		if(!email || !emailValCode){
//			Tips('myModallyf_content_admin', "邮箱及其验证码不能为空！");
//			return ;
//		}
		if(!validateEmail(email)){
			Tips('myModallyf_content_admin', "邮箱格式不正确！");
			return ;
		}
	}
	
	if(login_needValPhone){
		if(!phone || !mblphValCode){
			Tips('myModallyf_content_admin', "手机号及其验证码不能为空！");
			return ;
		}
		if(!validatePhone(phone)){
			Tips('myModallyf_content_admin', "手机号码格式不正确！");
			return ;
		}
	}
	
		$.ajax({
			url : '/jf/puresport/t6MgrAhr/ImproveAdminInfo',
			type : 'POST', // GET
			async : true, // 或false,是否异步
			data : {
				xiehuiItemName : xiehuiItemName,
				phone : phone,
				mblphValCode : mblphValCode,
				email : email,
				emailValCode : '',
			},
			timeout : 5000, // 超时时间
			dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
			beforeSend : function(xhr) {
				console.log('发送前')
			},
			success : function(data, textStatus, jqXHR) {

				if (data.flag) {
					window.location = data.url;

				} else {
					Tips('myModallyf_content_admin', data.msg);
				}
			},
			error : function(xhr, textStatus) {
				console.log('错误',xhr, textStatus);
			}
		});
}

function Tips(contentid, content) {
	document.getElementById(contentid).style.display = "block";
	$('#' + contentid).text(content);
}

function sendUserAuthCode(){
	var id = $("#form-id_user_forPwdBack").val().trim();
	var account = $("#form-email_user_forPwdBack").val().trim();
	sendAuthCode(id, account, '01');
}

function sendAdminAuthCode(){
	var id = $("#form-id_admin_forPwdBack").val().trim();
	var account = $("#form-email_admin_forpwdback").val().trim();
	sendAuthCode(id, account, '02');
}

function sendAuthCode(id, account, userOradmin) {
	console.log("sendAuthCode account", account);
	
	if(!id || id==''){
		loginAlert( "请您先输入身份证号！");
		return;
	}
	
	if(!account || account==''){
		loginAlert( "手机/邮箱不能为空！");
		return;
	}
		
	if(!validatePhone(account) && !validateEmail(account)){
		loginAlert("手机/邮箱格式不正确！");
		return ;
	} 
	sendConfirmcode2Email(id, account, userOradmin);
	
}

function sendConfirmcode2Email(id, account, userOradmin) {
	$.ajax({
		url : '/jf/puresport/pagesController/ForgetPwd_getConfirmcodeByEmail',
		type : 'POST', // GET
		async : true, // 或false,是否异步
		data : {
			id:id,
			account : account,
			userOradmin : userOradmin
		},
		timeout : 5000, // 超时时间
		dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
		beforeSend : function(xhr) {
			console.log('发送前')
		},
		success : function(data, textStatus, jqXHR) {

			if (data.flag) {
				loginAlert("验证码已发送, 请注意查收!");
				
			} else {
				loginAlert(data.msg);
			}
		}
	})
}

function forgetpwd_getpwdByEmail(userOradmin) {
	// alert(userOradmin);
	var id = '';
	var account = '';
	var confrimcode = '';
	var newPwd = '';
	var newPwd_confirm = '';
	if (userOradmin == '01')// 运动及辅助人员
	{
		id = $("#form-id_user_forPwdBack").val().trim();
		account = $("#form-email_user_forPwdBack").val().trim();
		confrimcode = $('#form-email_user_confirmCode').val().trim();
		newPwd = $('#form_user_newPwd_forPwdBack').val().trim();
		newPwd_confirm = $('#form_user_newPwd_confirm_forPwdBack').val().trim();
	} else {// 管理员
		id = $("#form-id_admin_forPwdBack").val().trim();
		account = $("#form-email_admin_forpwdback").val().trim();
		confrimcode = $('#form-email_admin_confirmcode_forpwaback').val().trim();
		newPwd = $('#form-admin_newpwd_forpwdback').val().trim();
		newPwd_confirm = $('#form-admin_confirmcode_forpwdback').val().trim();
	}
	if (id && newPwd && newPwd_confirm && account && confrimcode) {
		if (newPwd == newPwd_confirm) {
			$.ajax({
				url : '/jf/puresport/pagesController/ForgetPwd_setPwdByEmail',
				type : 'POST', // GET
				async : true, // 或false,是否异步
				data : {
					id:id,
					account : account,
					confrimcode : confrimcode,
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
					if (data.flag) {
						$('#myModallyf_content').text("密码重置成功！请重新登录！");
						$('#myModallyf').modal('show');
						$("#myModallyf_btn").click(function() {
							window.location = data.url;
						});
					} else {
						$('#myModallyf_content').text(data.msg);
						$('#myModallyf').modal('show');
						// alert(data.msg);
					}
				},
				error : function(xhr, textStatus) {
					console.log('错误',xhr, textStatus);
				}
			})
		} else {
			$('#myModallyf_content').text("两次输入密码不一致！！");
			$('#myModallyf').modal('show');
		}
	} else {
		$('#myModallyf_content').text("信息缺失！！");
		$('#myModallyf').modal('show');
	}
}

function resetPwd(userOradmin) {
	var oldPwd = $("#resetPwd_oldPwd").val();
	var newPwd = $("#resetPwd_newPwd").val();
	var newPwd_confrim = $("#resetPwd_newPwd_confirm").val();
	if (oldPwd && newPwd && newPwd_confrim) {
		if (newPwd != newPwd_confrim) {
			Tips('passwordModal_hint', "密码不一致！！");
			return;
		}
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
					
					if (data.flag) {

						layer.confirm('密码重置成功！请重新登录！', {
							icon : 3,
							title : '提示'
						}, function(index) {
							// do something
							window.location = cxt
									+ '/jf/puresport/pagesController/loginout';
							layer.close(index);
						});
					} else {
						var tips = data.msg;
						layer.alert('注册失败:' + tips, {
							skin : 'layui-layer-molv' ,
							closeBtn : 0
						});
					}
				},
				error : function(xhr, textStatus) {
					console.log('错误')
					console.log(xhr)
					console.log(textStatus)
				},
				complete : function() {
					console.log('结束')
				}
		})
	} else {
		Tips('passwordModal_hint', "信息缺失！！");
	}
}

function initScoreTable(userID) {
	// alert(userID);
	if (userID) {
		$
				.ajax({
					url : '/jf/puresport/T11ExamStat/get_exam_grd',
					type : 'POST', // GET
					async : true, // 或false,是否异步
					data : {
						userID : userID
					},
					timeout : 5000, // 超时时间
					dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
					beforeSend : function(xhr) {
						// console.log(xhr)
						console.log('发送前')
					},
					success : function(data, textStatus, jqXHR) {
						if (data.flag) {
							// alert(data);
							// console.log(data.itemlist[0].exam_grd);
							var dataSet = [];
							for (var i = 0; i < data.itemlist.length; i++) {
								var score = [];
								score.push(data.itemlist[i].exam_grd);
								score.push(data.itemlist[i].exam_name);
								score.push(data.itemlist[i].tms);
								score.push(data.itemlist[i].examid);
								score.push(data.itemlist[i].usrid);
								dataSet.push(score);
							}
							$('#score_excel')
									.DataTable(
											{
												data : dataSet,
												language : {
													url : "/ui/DataTables/Chinese.json"
												},
												// "filter": false,
												// "destroy": true,
												columns : [
														{
															title : "成绩"
														},
														{
															title : "赛事"
														},
														{
															title : "时间"
														},
														{
															title : "查看试卷",
															sortable : false,
															render : function(
																	data, type,
																	row) {
																
																return '<a href="/jf/puresport/t7Crcl/queryTestPaper?exam_grd='
																		+ row[0]
																		+ '&tms='
																		+ row[2]
																		+ '&usrid='
																		+ row[4]
																		+ '&examid='
																		+ row[3]
																		+ '" target="_blank"  role="button">'
																		/*+ '<code class="text-success bg-success">答题情况</code>'*/
																		+ '<span class="badge">查看</span>'
																		+ '</a>';
															}
														}, ]
											});
						} else {
							var dataSet = [];
							$('#score_excel').DataTable({
								data : dataSet,
								language : {
									url : "/ui/DataTables/Chinese.json"
								},
								// "filter": false,
								// "destroy": true,
								columns : [ {
									title : "成绩"
								}, {
									title : "赛事"
								}, {
									title : "时间"
								} ]
							});
						}
					},
					error : function(xhr, textStatus) {
						console.log('错误')
						console.log(xhr)
						console.log(textStatus)
					},
					complete : function() {
						console.log('结束')
					}
				})
	}
}
