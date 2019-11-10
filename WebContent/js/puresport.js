/**
 * author:lyf
 * 
 */
var app = {};
function initlogin() {
	app.arr_xiehuiItemName = [ "请选择协会项目", "射箭", "田径", "垒球", '棒球', '羽毛球', '篮球',
			'沙滩排球', '拳击', '皮划艇', '自行车', '跳水', '马术', '击剑', '足球', '体操', '手球',
			'曲棍球', '柔道', '现代五项', '艺术体操', '赛艇', '帆船', '射击', '游泳', '花样游泳', '乒乓球',
			'跆拳道', '网球', '蹦床', '铁人三项', '排球', '水球', '举重', '摔跤', '高尔夫', '七人制橄榄球',
			'小轮车', '滑板', '冲浪', '攀岩', '空手道', '三人篮球', '轮滑', '保龄球', '卡巴迪', '藤球',
			'壁球', '武术', '桥牌', '滑翔伞', '水上摩托', '电子竞技', '花样滑冰', '冰球', '速度滑冰',
			'跳台滑雪', '北欧两项', '越野滑雪', '有舵雪橇', '雪车', '冬季两项', '冰壶', '俯式冰橇', '无舵雪橇',
			'高山滑雪', '自由式滑雪', '短道速滑', '单板滑雪' ];

	app.arr_Competetion = [ "请选择赛事", "省运会", "亚运会", "青奥会" ];
	app.arr_CompetetionItem = [
			[ "请选择项目" ],
			[ "请选择项目", "足球", "笼式足球", "橄榄球", "自行车", "羽毛球", "棒垒球", "蹦床", "高尔夫",
					"花样游泳", "皮划艇", "射箭", "摔跤", "跆拳道", "田径", "曲棍球", "跳水", "网球",
					"武术", "艺术体操", "游泳", "击剑", "举重", "篮球", "排球", "乒乓球", "拳击",
					"柔道", "赛艇", "体操", "射击", "铁人三项", "马术", "气排球", "太极拳", "桥牌",
					"龙舟", "柔力球", "门球", "舞龙", "舞狮", "冰壶", "单板滑雪u型场地技巧",
					"单板滑雪平行大回转", "单板滑雪障碍追逐", "冬季两项", "短道速滑", "钢架雪车", "高山滑雪",
					"花样滑冰", "速度滑冰", "跳台滑雪", "雪车", "越野滑雪", "自由式滑雪u型场地",
					"自由式滑雪空中技巧", "自由式滑雪坡面障碍技巧", "自由式滑雪雪上技巧", "冰球" ],
			[ '请选择项目', "田径", "赛艇", "羽毛球", "垒球", "棒球", "篮球", "足球", "拳击", "皮划艇",
					"自行车", "击剑", "体操", "手球", "举重", "曲棍球", "柔道", "水上项目", "现代五项",
					"马术", "跆拳道", "乒乓球", "网球", "射击", "射箭", "铁人三项", "帆船帆板", "排球",
					"游泳", "空手道", "攀岩", "滑板", "冲浪", "保龄球", "卡巴迪", "藤球", "壁球",
					"武术", "桥牌", "滑翔伞", "水上摩托" ],
			[ '请选择项目', "游泳", "跳水", "射箭", "田径", "羽毛球", "篮球", "拳击", "皮划艇", "自行车",
					"马术", "击剑", "足球", "体操", "手球", "曲棍球", "柔道", "现代五项", "赛艇",
					"帆船", "射击", "乒乓球", "跆拳道", "网球", "沙滩手球", "沙滩排球", "举重", "摔跤",
					"高尔夫球", "橄榄球", "体育舞蹈", "空手道", "运动攀登" ]

	];
	// 协会
	var XiehuiItemName = document.getElementById("getxiehuiItemName");
	if (XiehuiItemName) {
		// 循环将数组中的数据写入<option>标记中
		for (var i = 0; i < app.arr_xiehuiItemName.length; i++) {
			var op = new Option(app.arr_xiehuiItemName[i],
					app.arr_xiehuiItemName[i]);
			XiehuiItemName.options.add(op);
		}
	}

	// 首先获取对象
	var Competetion = document.getElementById("getSportItem_user_Competetion");
	var CompetetionItem = document
			.getElementById("getSportItem_user_CompetetionItem");

	// 循环将数组中的数据写入<option>标记中
	// for(var i=0;i<app.arr_Competetion.length;i++){
	// var op = new Option(app.arr_Competetion[i],app.arr_Competetion[i]);
	// Competetion.options.add(op);
	// }
	var op = new Option(app.arr_Competetion[1], app.arr_Competetion[1]);
	Competetion.options.add(op);
	// 修改省份列表的默认选择项
	var index = 0;
	Competetion.selectedIndex = index;

	// 循环将数组中的数据写入<option>标记中
	// for (var j = 0; j<app.arr_CompetetionItem[index].length;j++) {
	//		
	// var op = new
	// Option(app.arr_CompetetionItem[index][j],app.arr_CompetetionItem[index][j]);
	// CompetetionItem.options.add(op);
	// }
	for (var j = 0; j < app.arr_CompetetionItem[1].length; j++) {

		var op = new Option(app.arr_CompetetionItem[1][j],
				app.arr_CompetetionItem[1][j]);
		CompetetionItem.options.add(op);
	}
}

function changeSelect_user_Competetion(index) {
	// 选择对象
	var CompetetionItem = document
			.getElementById("getSportItem_user_CompetetionItem");
	// 清空二级菜单选项
	CompetetionItem.options.length = 0;

	// 循环将数组中的数据写入<option>标记中
	for (var j = 0; j < app.arr_CompetetionItem[index].length; j++) {

		var op = new Option(app.arr_CompetetionItem[index][j],
				app.arr_CompetetionItem[index][j]);
		CompetetionItem.options.add(op);

	}
}

function loginAlert(msg) {
	$('#myModallyf_content').text(msg);
	$('#myModallyf').modal('show');
}

var login_needValSptPrj = false;
var login_needValEmail = false;
var login_needValPhone = false;

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

	// alert(hex_md5($("#form-pwd_user").val()));
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

			console.log("userLogin", data);
			if (data.flag) {
				app.userType = data.userType;
				if (data.needImproveInfoOrNot) {
					$("#userLoginPanel_1").toggle();
					$("#userLoginPanel_2").toggle();

//					if (data.needValSptPrj) {
//						login_needValSptPrj = true;
//						$('#getSportItem_user').show();
//					}
					if (data.needValPhone) {
						login_needValPhone = true;
						$('#divValPhone').show();
					}
					if (data.needValEmail) {
						login_needValEmail = true;
						$('#divValEmail').show();
					}

					if (data.belongToInstitute) {
						app.belongToInstitute = data.belongToInstitute;
						$("#getSportItem_user_assist").toggle();
					}
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
	// alert(user_type);
	if (user_type == "0")// 请选择人员类型
	{
		// alert(user_type);
		app.userType = "请选择人员类型";
		$(".form-loginbox_user_2_1").toggle(false);
		$(".form-loginbox_user_2_2").toggle(false);
	}
	if (user_type == "1")// 运动员
	{
		app.userType = "运动员";
		$(".form-loginbox_user_2_1").toggle(true);
		$(".form-loginbox_user_2_2").toggle(false);
	} else {// 辅助人员
		app.userType = "辅助人员";
		$(".form-loginbox_user_2_2").toggle(true);
		$(".form-loginbox_user_2_1").toggle(false);
	}
}
function admin_login() {

	$.ajax({
				url : '/jf/puresport/t6MgrAhr/login',
				type : 'POST', // GET
				async : true, // 或false,是否异步
				data : {
					account : $("#form-phone_admin").val(),
					pwd : $("#form-pwd_admin").val(),
					authCode : $("#authCode_admin").val()
				},
				timeout : 5000, // 超时时间
				dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
				beforeSend : function(xhr) {
					// console.log(xhr)
					console.log('发送前')
				},
				success : function(data, textStatus, jqXHR) {

					if (data.flag) {
						console.log(data);
						if (data.needImproveInfoOrNot) {
							$("#adminLoginPanel_1").toggle();
							$("#adminLoginPanel_2").toggle();
							app.needImproveInstituteOrNot = data.needImproveInstituteOrNot;
							// app.needImproveWrk_unitAndPostOrNot =
							// data.needImproveWrk_unitAndPostOrNot;
							if (data.needImproveInstituteOrNot) {
								// alert(data.needImproveInstituteOrNot);
								$(".form-loginbox_admin_1").toggle(true);
							} else {
								$(".form-loginbox_admin_1").toggle(false);
							}

						} else {
							window.location = data.url;
						}

					} else {
						// alert(data.msg);
						$('#myModallyf_content').text(data.msg);
						$('#myModallyf').modal('show');
					}
				},
				error : function(xhr, textStatus) {
					console.log('错误')
					console.log(xhr)
					console.log(textStatus)
				}
			})

}
function Improve_user_info() {
	if (app.userType == "运动员")// 运动员
	{

		var phone = $('#mblph_no').val().trim();
		var mblphValCode = $('#mblphValCode').val().trim();
		
		var email = $('#email').val().trim();
		var emailValCode = $('#emailValCode').val().trim();
		
		if(login_needValEmail){
			if(!email || !emailValCode){
				Tips('myModallyf_content_user', "邮箱及其验证码不能为空！");
				return ;
			}
			if(!validateEmail(email)){
				Tips('myModallyf_content_user', "邮箱格式不正确！");
				return ;
			}
		}
		
		if(login_needValPhone){
			if(!phone || !mblphValCode){
				Tips('myModallyf_content_user', "手机号及其验证码不能为空！");
				return ;
			}
			if(!validatePhone(phone)){
				Tips('myModallyf_content_user', "手机号码格式不正确！");
				return ;
			}
		}

		$.ajax({
			url : '/jf/puresport/t1usrBsc/ImproveUserInfo',
			type : 'POST', // GET
			async : true, // 或false,是否异步
			data : {
				usertype : app.userType,
				//competetion : Competetion,
				//competetionitem : CompetetionItem,
				phone : phone,
				mblphValCode : mblphValCode,
				email : email,
				emailValCode : emailValCode,

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

					Tips('myModallyf_content_user', data.msg);
				}
			},
			error : function(xhr, textStatus) {
				console.log('错误')
				console.log(xhr)
				console.log(textStatus)
			}
		})

	}
	if (app.userType == "辅助人员") {// 辅助人员
		var CompetetionItem_user_assist = $(
				'#getSportItem_user_assist_CompetetionItem option:selected')
				.val();
		if (app.belongToInstitute) {
			if (CompetetionItem_user_assist == "请选择项目") {
				Tips('myModallyf_content_user', "请选择项目!");
				return;
			}
		}
		var company = $('#form-company_assist').val();
		var position = $('#form-position_assist').val();
		if (company && position) {
			$.ajax({
				url : '/jf/puresport/t1usrBsc/ImproveUserInfo',
				type : 'POST', // GET
				async : true, // 或false,是否异步
				data : {
					usertype : app.userType,
					belongToInstitute : app.belongToInstitute,
					CompetetionItem_user_assist : CompetetionItem_user_assist,
					company : company,
					position : position
				},
				timeout : 5000, // 超时时间
				dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
				beforeSend : function(xhr) {
					// console.log(xhr)
					console.log('发送前')
				},
				success : function(data, textStatus, jqXHR) {

					if (data.flag) {
						window.location = data.url;
					} else {

						Tips('myModallyf_content_user', data.msg);
					}
				},
				error : function(xhr, textStatus) {
					console.log('错误')
					console.log(xhr)
					console.log(textStatus)
				}
			})
		} else {
			Tips('myModallyf_content_user', "请完善个人信息！");
		}
	}

}
function Improve_user_info_selfcenter(usr_tp) {
	// alert(usr_tp);
	app.userType = usr_tp;
	Improve_user_info();
}
// function Improve_user_Assist_info()
// {
//
// console.log($('#form-company_assist').val());
// console.log($('#form-position_assist').val());
//	
// }
function Improve_admin_info() {

	var xiehuiItemName = $('#getxiehuiItemName option:selected').val();

	if (app.needImproveInstituteOrNot) {

		if (xiehuiItemName == "请选择协会项目") {
			alert("请选择协会项目");
			return;
		}
	}

	// if(company&&position)
	if (xiehuiItemName) {
		$.ajax({
			url : '/jf/puresport/t6MgrAhr/ImproveAdminInfo',
			type : 'POST', // GET
			async : true, // 或false,是否异步
			data : {

				// company:company,
				// position:position
				xiehuiItemName : xiehuiItemName
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
				console.log('错误')
				console.log(xhr)
				console.log(textStatus)
			},
			complete : function() {
				console.log('结束')
			}
		})
	} else {
		Tips('myModallyf_content_admin', "请完善个人信息！");
	}
}
function Tips(contentid, content) {
	document.getElementById(contentid).style.display = "block";
	$('#' + contentid).text(content);
}

function sendUserAuthCode(){
	var account = $("#form-email_user_forPwdBack").val().trim();
	sendAuthCode(account, '01');
}

function sendAdminAuthCode(){
	var account = $("#form-email_admin_forpwdback").val().trim();
	sendAuthCode(account, '02');
}

function sendAuthCode(account, userOradmin) {
	console.log("sendAuthCode account", account);
	if(!account || account==''){
		console.log("1");
		loginAlert( "手机/邮箱不能为空！");
		return;
	}
		
	if(!validatePhone(account) && !validateEmail(account)){
		console.log("2");
		loginAlert("手机/邮箱格式不正确！");
		return ;
	} 
	sendConfirmcode2Email(account, userOradmin);
	
}

function sendConfirmcode2Email(account, userOradmin) {
	$.ajax({
		url : '/jf/puresport/pagesController/ForgetPwd_getConfirmcodeByEmail',
		type : 'POST', // GET
		async : true, // 或false,是否异步
		data : {
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
	var account = '';
	var confrimcode = '';
	var newPwd = '';
	var newPwd_confirm = '';
	if (userOradmin == '01')// 运动及辅助人员
	{
		account = $("#form-email_user_forPwdBack").val();
		confrimcode = $('#form-email_user_confirmCode').val();
		newPwd = $('#form_user_newPwd_forPwdBack').val();
		newPwd_confirm = $('#form_user_newPwd_confirm_forPwdBack').val();
	} else {// 管理员
		account = $("#form-email_admin_forpwdback").val();
		confrimcode = $('#form-email_admin_confirmcode_forpwaback').val();
		newPwd = $('#form-admin_newpwd_forpwdback').val();
		newPwd_confirm = $('#form-admin_confirmcode_forpwdback').val();
	}
	if (newPwd && newPwd_confirm && account && confrimcode) {
		if (newPwd == newPwd_confirm) {
			// alert(newPwd);
			$.ajax({
				url : '/jf/puresport/pagesController/ForgetPwd_setPwdByEmail',
				type : 'POST', // GET
				async : true, // 或false,是否异步
				data : {
					// userType:app.userType,
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
					console.log('错误')
					console.log(xhr)
					console.log(textStatus)
				},
				complete : function() {
					console.log('结束')
				}
			})
		} else {
			// alert("两次输入密码不一致！！");
			$('#myModallyf_content').text("两次输入密码不一致！！");
			$('#myModallyf').modal('show');
		}
	} else {
		// alert("信息缺失！！");
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
					if (data.flag) {
						Tips('passwordModal_hint', "密码重置成功！请重新登录！");
					} else {
						Tips('passwordModal_hint', data.msg);
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
