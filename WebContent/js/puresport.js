/**
 * author:lyf

 */
var app={};
function initlogin()
{
	
	app.arr_Competetion = ["请选择赛事","省运会","亚运会","青奥会"];
	app.arr_CompetetionItem = [
		["请选择项目"],
		["请选择项目","足球","笼式足球","橄榄球","自行车","羽毛球","棒垒球","蹦床","高尔夫","花样游泳","皮划艇","射箭","摔跤","跆拳道","田径","跳水","网球",
			"武术","艺术体操","游泳","击剑","举重","篮球","排球","乒乓球","拳击","柔道","赛艇","体操","射击","铁人三项","马术","气排球","太极拳",
			"桥牌","龙舟","柔力球","门球","舞龙","舞狮"],
		['请选择项目',"田径","赛艇","羽毛球","垒球","棒球","篮球","足球","拳击","皮划艇","自行车","击剑","体操","手球","举重","曲棍球","柔道","水上项目","现代五项","马术","跆拳道",
			"乒乓球","网球","射击","射箭","铁人三项","帆船帆板","排球","游泳","空手道","攀岩","滑板","冲浪","保龄球","卡巴迪","藤球","壁球","武术","桥牌","滑翔伞","水上摩托"],
		['请选择项目',"游泳","跳水","射箭","田径","羽毛球","篮球","拳击","皮划艇","自行车","马术","击剑","足球","体操","手球",
			"曲棍球","柔道","现代五项","赛艇","帆船","射击","乒乓球","跆拳道","网球","沙滩手球","沙滩排球","举重","摔跤",
			"高尔夫球","橄榄球","体育舞蹈","空手道","运动攀登"]
		
	];
	
	 //首先获取对象
	var Competetion = document.getElementById("getSportItem_user_Competetion");
	var CompetetionItem = document.getElementById("getSportItem_user_CompetetionItem");
	
	//循环将数组中的数据写入<option>标记中
//	for(var i=0;i<app.arr_Competetion.length;i++){
//		var op = new Option(app.arr_Competetion[i],app.arr_Competetion[i]);  
//		 Competetion.options.add(op);  
//	}
	var op = new Option(app.arr_Competetion[1],app.arr_Competetion[1]);  
	Competetion.options.add(op);  
	//修改省份列表的默认选择项
	var index = 0;
	Competetion.selectedIndex = index;
	
	//循环将数组中的数据写入<option>标记中
//	for (var j = 0; j<app.arr_CompetetionItem[index].length;j++) {
//		
//		var op = new Option(app.arr_CompetetionItem[index][j],app.arr_CompetetionItem[index][j]);  
//		CompetetionItem.options.add(op);
//	}
	for (var j = 0; j<app.arr_CompetetionItem[1].length;j++) {
		
		var op = new Option(app.arr_CompetetionItem[1][j],app.arr_CompetetionItem[1][j]);  
		CompetetionItem.options.add(op);
	}
}
function  changeSelect_user_Competetion(index){
	//选择对象
	var CompetetionItem =document.getElementById("getSportItem_user_CompetetionItem");
	//清空二级菜单选项  
	CompetetionItem.options.length=0;  
	
	//循环将数组中的数据写入<option>标记中
	for (var j = 0; j<app.arr_CompetetionItem[index].length;j++) {

		var op = new Option(app.arr_CompetetionItem[index][j],app.arr_CompetetionItem[index][j]);  
		CompetetionItem.options.add(op);
		
	}
}
	
function user_login()
{

//	alert(hex_md5($("#form-pwd_user").val()));
	$.ajax({
	    url:'/jf/puresport/t1usrBsc/login',
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:{
	    	account:$("#form-phone_user").val(),
	    	pwd:$("#form-pwd_user").val(),
	    },
	    timeout:5000,    //超时时间
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
	        //console.log(xhr)
	        console.log('发送前')
	    },
	    success:function(data,textStatus,jqXHR){
	    	//console.log(data);
	    	//window.location.href=data.reportUrl;
	    	//alert(data.reportUrl);
	    	//window.open(data.reportUrl,"_blank");
	    	if(data.flag) {  
	    		app.userType = data.userType;
	    		if(data.needImproveInfoOrNot)
	    		{
	    			$("#userLoginPanel_1").toggle();
		    		$("#userLoginPanel_2").toggle();
		    		if(data.userType == "运动员")//运动员
	    			{
		    			
		    			$(".form-loginbox_user_2_1").toggle(true);
		    			$(".form-loginbox_user_2_2").toggle(false);
	    			}
		    		else{//辅助人员
		    			$(".form-loginbox_user_2_2").toggle(true);
		    			$(".form-loginbox_user_2_1").toggle(false);
		    		}
	    		}
	    		else{
	    			window.location=data.url;  
	    		}
	
            }  
            else {  
//                alert(data.msg);  
                $('#myModallyf_content').text(data.msg);
	    		$('#myModallyf').modal('show');
            }  
	    },
	    error:function(xhr,textStatus){
	        console.log('错误')
	        console.log(xhr)
	        console.log(textStatus)
	    },
	    complete:function(){
	        console.log('结束')
	    }
	})
	
	
}
function admin_login()
{

	$.ajax({
	    url:'/jf/puresport/t6MgrAhr/login',
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:{
	    	account:$("#form-phone_admin").val(),
	    	pwd:$("#form-pwd_admin").val(),
	    },
	    timeout:5000,    //超时时间
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
	        //console.log(xhr)
	        console.log('发送前')
	    },
	    success:function(data,textStatus,jqXHR){
	    	//console.log(data);
	    	//window.location.href=data.reportUrl;
	    	//alert(data.reportUrl);
	    	//window.open(data.reportUrl,"_blank");
	    	if(data.flag) {  
	    		if(data.needImproveInfoOrNot)
	    		{
	    			$("#adminLoginPanel_1").toggle();
		    		$("#adminLoginPanel_2").toggle();
	    		}
	    		else{
	    			window.location=data.url;  
	    		}
                
            }  
            else {  
//                alert(data.msg);  
                $('#myModallyf_content').text(data.msg);
	    		$('#myModallyf').modal('show');
            }  
	    },
	    error:function(xhr,textStatus){
	        console.log('错误')
	        console.log(xhr)
	        console.log(textStatus)
	    },
	    complete:function(){
	        console.log('结束')
	    }
	})
	
	
}
function Improve_user_info()
{
	if(app.userType=="运动员")//运动员
	{
//		alert(app.userType);
		var group1=false;var code = "";
		var group2=false;
//		$().distpicker('getDistricts'); // 中国
//		$().distpicker('getDistricts', 330000); // 浙江省
	//console.log($("#getDist_user").distpicker('getDistricts', 330100)); // 杭州市
		//console.log($("#getDist_user").distpicker('output',"province")); // 中国
		var province = $('#getDist_user_province option:selected').val();
		var city = $('#getDist_user_city option:selected').val();
		var district = $('#getDist_user_district option:selected').val();
		if(province||city||district)
		{
			group1=true;
			if(district)
			{
				code = district;
			}
			else if(city)
			{
				code = city;
			}
			else
			{
				code = province;
			}
			
		}
		else
		{
			group1=false;
//			alert("请选择代表单位");
//			$('#myModallyf_content').text("请选择代表单位");
//	    	$('#myModallyf').modal('show');
//			document.getElementById('myModallyf_content_user').style.display="block";
//			$('#myModallyf_content_user').text("请选择代表单位");
			Tips('myModallyf_content_user',"请选择代表单位!");
			return;
			
		}
		var Competetion = $('#getSportItem_user_Competetion option:selected').val();
		
		var CompetetionItem = $('#getSportItem_user_CompetetionItem option:selected').val();
		if(Competetion=="请选择赛事"||CompetetionItem=="请选择项目")
		{
			group2=false;
//			alert("请选择运动项目");
//			$('#myModallyf_content').text("请选择运动项目");
//	    	$('#myModallyf').modal('show');
//			$('#myModallyf_content_user').style.display="block";
//			$('#myModallyf_content_user').text("请选择运动项目");
			Tips('myModallyf_content_user',"请选择运动项目!");
			return;
		}
		else{
			group2=true;
		}
		if(group1&&group2)
		{
			//alert(code+","+Competetion+","+CompetetionItem);
			$.ajax({
			    url:'/jf/puresport/t1usrBsc/ImproveUserInfo',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:{
//			    	userType:app.userType,
			    	code:code,
			    	competetion:Competetion,
			    	competetionitem:CompetetionItem
			    },
			    timeout:5000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    beforeSend:function(xhr){
			        //console.log(xhr)
			        console.log('发送前')
			    },
			    success:function(data,textStatus,jqXHR){
			    	//console.log(data);
			    	//window.location.href=data.reportUrl;
			    	//alert(data.reportUrl);
			    	//window.open(data.reportUrl,"_blank");
			    	if(data.flag) {  
		                window.location=data.url;  
			    		
		            }  
		            else {  
//		                alert(data.msg);  
//		                $('#myModallyf_content').text(data.msg);
//		    	    	$('#myModallyf').modal('show');
//		            	$('#myModallyf_content_user').style.display="block";
//		    			$('#myModallyf_content_user').text(data.msg);
		            	Tips('myModallyf_content_user',data.msg);
		            }  
			    },
			    error:function(xhr,textStatus){
			        console.log('错误')
			        console.log(xhr)
			        console.log(textStatus)
			    },
			    complete:function(){
			        console.log('结束')
			    }
			})
		}
	}
	else{//辅助人员
		var company = $('#form-company_assist').val();
		var position = $('#form-position_assist').val();
		if(company&&position)
		{
			$.ajax({
			    url:'/jf/puresport/t1usrBsc/ImproveUserInfo',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:{
//			    	userType:app.userType,
			    	company:company,
			    	position:position
			    },
			    timeout:5000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    beforeSend:function(xhr){
			        //console.log(xhr)
			        console.log('发送前')
			    },
			    success:function(data,textStatus,jqXHR){
			    	//console.log(data);
			    	//window.location.href=data.reportUrl;
			    	//alert(data.reportUrl);
			    	//window.open(data.reportUrl,"_blank");
			    	if(data.flag) {  
		                window.location=data.url;  
			    	}  
		            else {  
//		                alert(data.msg); 
//		                $('#myModallyf_content').text(data.msg);
//		    	    	$('#myModallyf').modal('show');
//		            	$('#myModallyf_content_user').style.display="block";
//		    			$('#myModallyf_content_user').text(data.msg);
		            	Tips('myModallyf_content_user',data.msg);
		            }  
			    },
			    error:function(xhr,textStatus){
			        console.log('错误')
			        console.log(xhr)
			        console.log(textStatus)
			    },
			    complete:function(){
			        console.log('结束')
			    }
			})
		}
		else{
//			alert("请完善个人信息！");
//			$('#myModallyf_content').text("请完善个人信息！");
//	    	$('#myModallyf').modal('show');
//			$('#myModallyf_content_user').style.display="block";
//			$('#myModallyf_content_user').text("请完善个人信息！");
			Tips('myModallyf_content_user',"请完善个人信息！");
		}
	}
	
	
}
function Improve_user_info_selfcenter(usr_tp)
{
//	alert(usr_tp);
	app.userType = usr_tp;
	Improve_user_info();
}
//function Improve_user_Assist_info()
//{
//
//	console.log($('#form-company_assist').val());
//	console.log($('#form-position_assist').val());
//	
//}
function Improve_admin_info()
{

	var company = $('#form-company_admin').val();
	var position = $('#form-position_admin').val();
	
	if(company&&position)
	{
		$.ajax({
		    url:'/jf/puresport/t6MgrAhr/ImproveAdminInfo',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
//		    	userType:app.userType,
		    	company:company,
		    	position:position
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		        //console.log(xhr)
		        console.log('发送前')
		    },
		    success:function(data,textStatus,jqXHR){
		    	//console.log(data);
		    	//window.location.href=data.reportUrl;
		    	//alert(data.reportUrl);
		    	//window.open(data.reportUrl,"_blank");
		    	if(data.flag) {  
	                window.location=data.url;  
		    		
	            }  
	            else {  
//	                alert(data.msg);  
//	            	 $('#myModallyf_content_user').style.display="block";
//	                $('#myModallyf_content_user').text(data.msg);
//	    	    	$('#myModallyf').modal('show');
	            	Tips('myModallyf_content_admin',data.msg);
	            }  
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束')
		    }
		})
	}
	else{
//		alert("请完善个人信息！");
//		 $('#myModallyf_content').text("请完善个人信息！");
//		 $('#myModallyf').modal('show');
		Tips('myModallyf_content_admin',"请完善个人信息！");
	}
}
function Tips(contentid,content)
{
	document.getElementById(contentid).style.display = "block";
	$('#'+contentid).text(content);
}
function get_email_confirmcode(userOradmin)
{
	
	//form-email_user_forPwdBack
	//form-email_user_confirmCode
	if(userOradmin=='01')
	{
		var email = $("#form-email_user_forPwdBack").val();
		//alert(email);
		if(email)
		{
			sendConfirmcode2Email(email,userOradmin);
		}
		else{
//			alert("请先输入邮箱！");
			$('#myModallyf_content').text("请先输入邮箱！");
			$('#myModallyf').modal('show');
		}
	}
	else
	{
		var email = $("#form-email_admin_forpwdback").val();
		if(email)
		{
			sendConfirmcode2Email(email,userOradmin);                                                                                                                                                                                                                                                                                                                                                                                                                
		}
		else{
//			alert("请先输入邮箱！");
			$('#myModallyf_content').text("请先输入邮箱！");
			$('#myModallyf').modal('show');
		}
	}
	
}

function sendConfirmcode2Email(email,userOradmin)
{
	$.ajax({
	    url:'/jf/puresport/pagesController/ForgetPwd_getConfirmcodeByEmail',
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:{
//	    	userType:app.userType,
	    	email:email,
	    	userOradmin:userOradmin
	    },
	    timeout:5000,    //超时时间
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
	        //console.log(xhr)
	        console.log('发送前')
	    },
	    success:function(data,textStatus,jqXHR){
	    	//console.log(data);
	    	//window.location.href=data.reportUrl;
	    	//alert(data.reportUrl);
	    	//window.open(data.reportUrl,"_blank");
	    	if(data.flag) {  
                //window.location=data.url;  
	    		//alert("验证码已发送邮箱！");
	    		$('#myModallyf_content').text("验证码已发送邮箱!");
	    		$('#myModallyf').modal('show');
            }  
            else {  
//                alert(data.msg); 
                $('#myModallyf_content').text(data.msg);
	    		$('#myModallyf').modal('show');
            }  
	    },
	    error:function(xhr,textStatus){
	        console.log('错误')
	        console.log(xhr)
	        console.log(textStatus)
	    },
	    complete:function(){
	        console.log('结束')
	    }
	})
}
function forgetpwd_getpwdByEmail(userOradmin)
{
	//alert(userOradmin);
	var email = '';
	var confrimcode = '';
	var newPwd='';
	var newPwd_confirm = '';
	if(userOradmin=='01')//运动及辅助人员
	{
		email = $("#form-email_user_forPwdBack").val();
		confrimcode = $('#form-email_user_confirmCode').val();
		newPwd = $('#form_user_newPwd_forPwdBack').val();
		newPwd_confirm = $('#form_user_newPwd_confirm_forPwdBack').val();
	}
	else{//管理员
		email = $("#form-email_admin_forpwdback").val();
		confrimcode = $('#form-email_admin_confirmcode_forpwaback').val();
		newPwd = $('#form-admin_newpwd_forpwdback').val();
		newPwd_confirm = $('#form-admin_confirmcode_forpwdback').val();
	}
	if(newPwd&&newPwd_confirm&&email&&confrimcode)
	{
		if(newPwd==newPwd_confirm)
		{
			//alert(newPwd);
			$.ajax({
			    url:'/jf/puresport/pagesController/ForgetPwd_setPwdByEmail',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:{
//			    	userType:app.userType,
			    	email:email,
			    	confrimcode:confrimcode,
			    	newPwd:newPwd,
			    	userOradmin:userOradmin
			    },
			    timeout:5000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    beforeSend:function(xhr){
			        //console.log(xhr)
			        console.log('发送前')
			    },
			    success:function(data,textStatus,jqXHR){
			    	if(data.flag) {  
		                //window.location=data.url;  
			    		//alert("密码重置成功！请重新登录！");
			    		
			    		$('#myModallyf_content').text("密码重置成功！请重新登录！");
			    		$('#myModallyf').modal('show');
			    		$("#myModallyf_btn").click(function(){
			    			window.location=data.url;
			    		  });
		            }  
		            else {  
		            	$('#myModallyf_content').text(data.msg);
			    		$('#myModallyf').modal('show');
		                //alert(data.msg);  
		            }  
			    },
			    error:function(xhr,textStatus){
			        console.log('错误')
			        console.log(xhr)
			        console.log(textStatus)
			    },
			    complete:function(){
			        console.log('结束')
			    }
			})
		}
		else{
//			alert("两次输入密码不一致！！");
			$('#myModallyf_content').text("两次输入密码不一致！！");
    		$('#myModallyf').modal('show');
		}
	}	
	else{
//		alert("信息缺失！！");
		$('#myModallyf_content').text("信息缺失！！");
	    $('#myModallyf').modal('show');
	}
}
function resetPwd(userOradmin)
{
	var oldPwd = $("#resetPwd_oldPwd").val();
	var newPwd = $("#resetPwd_newPwd").val();
	var newPwd_confrim = $("#resetPwd_newPwd_confirm").val();
	if(oldPwd&&newPwd&&newPwd_confrim)
	{
		if(newPwd!=newPwd_confrim)
		{
//			$('#passwordModal_hint').text("密码不一致！！");
			Tips('passwordModal_hint',"密码不一致！！");
		}
		else{
			$.ajax({
			    url:'/jf/puresport/pagesController/ResetPwd',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:{
//			    	userType:app.userType,
			    	oldPwd:oldPwd,
			    	newPwd:newPwd,
			    	userOradmin:userOradmin
			    },
			    timeout:5000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    beforeSend:function(xhr){
			        //console.log(xhr)
			        console.log('发送前')
			    },
			    success:function(data,textStatus,jqXHR){
			    	if(data.flag) {  
		                //window.location=data.url;  
			    		//alert("密码重置成功！请重新登录！");
			    		
//			    		$('#passwordModal_hint').text("密码重置成功！请重新登录！");
			    		Tips('passwordModal_hint',"密码重置成功！请重新登录！");
			    		//$('#passwordModal').modal('hide');
			    		
		            }  
		            else {  
//		            	$('#passwordModal_hint').text(data.msg);
		            	Tips('passwordModal_hint',data.msg);
//			    		$('#systemModal').modal('show');
		                //alert(data.msg);  
		            }  
			    },
			    error:function(xhr,textStatus){
			        console.log('错误')
			        console.log(xhr)
			        console.log(textStatus)
			    },
			    complete:function(){
			        console.log('结束')
			    }
			})
		}
	}
	else
	{
//		$('#passwordModal_hint').text("信息缺失！！");
		Tips('passwordModal_hint',"信息缺失！！");
	    //$('#systemModal').modal('show');
	}
}
function initScoreTable(userID)
{
//	alert(userID);
	if(userID)
	{
		$.ajax({
		    url:'/jf/puresport/t10ExamGrd/get_exam_grd',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		    	userID:userID
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		        //console.log(xhr)
		        console.log('发送前')
		    },
		    success:function(data,textStatus,jqXHR){
		    	if(data.flag) {  
//	                console.log(data.itemlist[0].exam_grd);
		    		var dataSet = [];
		    		for(var i =0;i<data.itemlist.length;i++)
		    		{
		    			var score = [];
		    			score.push(data.itemlist[i].exam_grd);
		    			score.push(data.itemlist[i].tms);
		    			dataSet.push(score);
		    		}
		    		$('#score_excel').DataTable({
	    		        data: dataSet,
	    		        language: {
	    		            url: "/ui/DataTables/Chinese.json"
	    		        },
//	    		        "filter": false,
//	    		        "destroy": true,
	    		        columns: [
	    		            { title: "成绩" },
	    		            { title: "时间" },
	    		            { title: "赛事" }
	    		        ]
	    		    });
	            }  
	            else { 
	            	var dataSet = [];
	            	$('#score_excel').DataTable({
	    		        data: dataSet,
	    		        language: {
	    		            url: "/ui/DataTables/Chinese.json"
	    		        },
//	    		        "filter": false,
//	    		        "destroy": true,
	    		        columns: [
	    		            { title: "成绩" },
	    		            { title: "时间" },
	    		            { title: "赛事" }
	    		        ]
	    		    });
	            }  
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束')
		    }
		})
	}
}
//function test()
//{
//	alert("ooo");
//}