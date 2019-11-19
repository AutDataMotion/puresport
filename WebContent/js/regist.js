
      // -----validate
        function validateComm(rule, v,lenMin, lenMax){
        	if(!v) return false;
        	if(v.length < lenMin || v.length > lenMax){
        		return false;
        	}
        	if(!rule.test(v)){
        		return false;
        	}
        	return true;
        }
        function validatePhone(v) {
        	var reg = /(^1\d{10}$)|(^[0-9]\d{7}$)/;
        	return validateComm(reg, v, 11, 11);
        };
        function validateEmail (v) {
        	var reg=/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        	return validateComm(reg, v, 5, 50);
            
        };
   
$(function() {
	layui.use([ 'layer', 'form', 'laydate'], function() {
		var layer = layui.layer, form = layui.form, laydate=layui.laydate;

		// ========init
		laydate.render({ 
			  elem: '#brth_dt'
			  ,min: '1960-1-1'
			  ,max: '2030-1-1'
			});
		
        setProvinceAjax();

		// ========Event

		form.on('select(province)', function (data) {
            setCityAjax(data.value); 
        });
		
		function btnSetDisable(domObj, disabled, text){
			if(disabled===true){
				domObj.prop('disabled', true);
				domObj.css({'background-color':'rgb(160, 162, 163)'});
				if(text){
					domObj.text(text);
				}
				
			}else {
				domObj.prop('disabled', false);
				domObj.css({'background-color':'rgb(30, 159, 255)'});
				if(text){
					domObj.text(text);
				}
			}
		}
		
		layui.$('#btn-val-phone').on('click', function(){
			var phone = $('#mblph_no').val();
			console.log(phone);
			if(!validatePhone(phone)){
				layer.msg('手机号码格式不正确，请确认！');
				return ;
			}
			var btnValPhone = $('#btn-val-phone');
			btnSetDisable(btnValPhone, true, "正在发送...");
			ajaxSendAuthCode(1, 'sendPhoneCode', {phone: phone, module:1}, '验证码已发送到您手机,请注意查收', '验证码发送失败，请重试或联系管理员', btnValPhone);
			btnSetDisable(btnValPhone, false, "点击获取"); 
		    
		});
		
//		layui.$('#btn-val-email').on('click', function(){
//			var email = $('#email').val();
//			console.log(email);
//			if(!validateEmail(email)){
//				layer.msg('邮箱格式不正确，请确认！');
//				return ;
//			}
//			var btnValEmail = $('#btn-val-email');
//			btnSetDisable(btnValEmail, true, "正在发送...");
//			ajaxSendAuthCode(2, 'sendEmailCode', {email: email, module:1}, '验证码已发送到您邮箱,请注意查收', '验证码发送失败，请重试或联系管理员', btnValEmail);
//			btnSetDisable(btnValEmail, false, "点击获取"); 
//		    
//		});
		
		form.on('submit(btn-submit)', function(data){
			var jsonStr = JSON.stringify(data.field);
//		    console.log(jsonStr);
		    user_regist(jsonStr);
		    return false;
		});
		  
		// ======== ajax
		var countdownPhone = 60;
		var countdownEmail = 60;
		
        function setTime(flag, obj) {
        	var countdown = flag===1?countdownPhone:countdownEmail;
        	
            if (countdown == 0) {
            	btnSetDisable(obj, false, "点击获取");
                if(flag===1){
                	countdownPhone = 60;
                } else {
                	countdownEmail = 60;
                }
                return;
            } else {
            	btnSetDisable(obj, true, "剩余"+countdown+"秒");
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
                    	layer.msg(res.tipStrings.join("  "));
                    }
                },
                error:function(){
                	layer.msg(tipFail);
                }
            })
		}
		
		function user_regist(formObjJsonStr) {

			$.ajax({
				url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/regist")),
				type : 'POST', // GET
				async : false, // 或false,是否异步
				data : {
					v : formObjJsonStr
				},
				timeout : 5000, // 超时时间
				dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
				beforeSend : function(xhr) {
					console.log('发送前 xhr', xhr)
				},
				success : function(data, textStatus, jqXHR) {

					if (data.hasSuc) {

						layer.confirm('注册成功，系统将跳转到登录页面！', {
							icon : 3,
							title : '提示'
						}, function(index) {
							// do something
							window.location = cxt
									+ '/jf/puresport/pagesController/login';
							layer.close(index);
						});
					} else {
						var tips = data.tipStrings.join("  ");
						layer.alert('注册失败:' + tips, {
							skin : 'layui-layer-molv' ,
							closeBtn : 0
						});
					}
				},
				error : function(xhr, textStatus) {
					console.log('错误', xhr, textStatus);
					layer.alert('系统错误，请联系管理员');
				}
			})
		}
	    
	    function setProvinceAjax(){
	    	
	    	$.ajax({
                url:encodeURI(encodeURI(cxt + "/jf/puresport/area/fetchProvinces")),
                data:{},
                dataType:"json",
                type:"get",
                timeout:5000,   
                async : true,
                cache : true,
                success:function(provinceList){
//                	console.log('fetchProvinces', provinceList)
                	
                	var $sel = $("#selProvince");

        	        // 获取对应省份城市
        	        for (var i = 0, len = provinceList.length; i < len; i++) {
        	            var provinceArea = provinceList[i];
        	            var option = $("<option value='" + provinceArea.name + "' pid='"+provinceArea.id+"'>" + provinceArea.name + "</option>");

        	            // 添加到 select 元素中
        	            $sel.append(option);
        	        }
        	        form.render('select');
        	        setCityAjax(provinceList[0].name);
        	        
                },
                error:function(){
                	layer.msg("获取信息失败");
                }
            })
	    }

	    function setCityAjax(provinceName) {
	    	$.ajax({
                url:encodeURI(encodeURI(cxt + "/jf/puresport/area/fetchCities")),
                data:{provinceName:provinceName},
                dataType:"json",
                type:"get",
                timeout:5000,   
                async : true,
                cache : true,
                success:function(cityList){
//                	console.log('cityList', cityList)
                	
                	var $city = $("#selCity");
                	var option, modelVal;

                	// 先清空之前绑定的值
        	        $city.empty();
        	        
        	        // 设置对应省份的城市
        	        for (var i = 0, len = cityList.length; i < len; i++) {
        	            modelVal = cityList[i];
        	            option = "<option value='" + modelVal.name + "'>" + modelVal.name + "</option>";
        	            $city.append(option);
        	        }
        	        form.render('select');
                },
                error:function(){
                	layer.msg("获取信息失败");
                }
            })
	    }

	}); // end layUI
	

});// end $function
