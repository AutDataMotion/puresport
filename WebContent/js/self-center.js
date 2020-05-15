selfCenterTest = false;
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
	
		var layer = layui.layer, form = layui.form, laydate=layui.laydate;

		var curUsrType = '运动员';
		
		// ========init
		laydate.render({ 
			  elem: '#brth_dt'
			  ,format: 'yyyy/MM/dd'
			  ,min: '1960/1/1'
			  ,max: '2030/1/1'
			});
		
        setProvinceAjax();

		// ========Event
        layui.$('#btn-update-userInfo').on('click', function(){
        	console.log("btn-update-userInfo click");
        	$.ajax({
                url:encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/selfCenterInfo")),
                data:{},
                dataType:"json",
                type:"get",
                timeout:5000,   
                async : false,
                cache : false,
                success:function(res){
                	console.log('selfCenterInfo ajax', res);
                	
                	// 初始化市
                	setCityAjax(res.province, res.city);
                	
                    // 表单赋值
                	form.val('athleteForm', {
                		"usr_tp":res.usr_tp
                        ,"nm": res.nm
                        ,"nm_char": res.nm_char
                        ,"crdt_no": res.crdt_no
                        ,"gnd": res.gnd
                        ,"brth_dt": res.brth_dt
                        ,"province": res.province
                        ,"city": res.city
                        ,"spt_prj": res.spt_prj
                        ,"typeleve": res.typeleve
                        ,"mblph_no": res.mblph_no
                        , "email":res.email
                        , "department":res.department
                        , "post":res.post
                      });
                	
                	curUsrType = res.usr_tp;
                	
                	if(res.usr_tp == '运动员'){
//      				  $('#divSportor').show();
      				  $('#divAssistor').hide();
      			  	} else {
      				  $('#divAssistor').show();
//      				  $('#divSportor').hide();
      			  	}
                	
                },
                error:function(){
                	layer.msg(tipFail);
                }
            });
          
        });
        
		form.on('select(province)', function (data) {
            setCityAjax(data.value);
           
        });
		
		
		form.on('radio(filter_usr_tp)', function(data){
			  console.log(data.value); //被点击的radio的value值
			  var usrType = data.value;
			  if(curUsrType == usrType){
				  return ;
			  }
			  curUsrType = usrType;
			  
			  if(curUsrType == '运动员'){
//				  $('#divSportor').show();
				  $('#divAssistor').hide();
			  } else {
				  $('#divAssistor').show();
//				  $('#divSportor').hide();
			  }
			  
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
			if(!validatePhone(phone)){
				layer.msg('手机号码格式不正确，请确认！');
				return ;
			}
			var btnValPhone = $('#btn-val-phone');
			btnSetDisable(btnValPhone, true, "正在发送...");
			ajaxSendAuthCode(1, 'sendPhoneCode', {phone: phone, module:1}, '验证码已发送到您手机,请注意查收', '验证码发送失败，请重试或联系管理员', btnValPhone);
			btnSetDisable(btnValPhone, false, "点击获取"); 
		});
		
		layui.$('#btn-val-email').on('click', function(){
			var email = $('#email').val();
			if(!validateEmail(email)){
				layer.msg('邮箱格式不正确，请确认！');
				return ;
			}
			
			var btnValEmail = $('#btn-val-email');
			btnSetDisable(btnValEmail, true, "正在发送...");
			ajaxSendAuthCode(2, 'sendEmailCode', {email: email, module:1}, '验证码已发送到您邮箱,请注意查收', '验证码发送失败，请重试或联系管理员', btnValEmail);
			btnSetDisable(btnValEmail, false, "点击获取"); 
		});
		
		form.on('submit(btn-submit-update)', function(data){
			
			if(!data.field.spt_prj || data.field.spt_prj == '请选择项目' || data.field.spt_prj == ''){
				layer.msg('项目不能为空');
				return false;
			}
			if(!data.field.typeleve){
				layer.msg('级别不能为空');
				return false;
			}
			
			if(data.field.usr_tp=='运动员'){
				
			} else {
				
				if(!data.field.department || data.field.department.trim() == ''){
					layer.msg('工作单位不能为空');
					return false;
				}
				if(!data.field.post || data.field.post.trim() == ''){
					layer.msg('职务不能为空');
					return false;
				}
			}

			var jsonStr = JSON.stringify(data.field);
		    user_update(jsonStr);
		    return false;
		});
		  
		// ======== ajax
		var countdownPhone = 60;
		var timerInterruptPhone = false;
		var countdownEmail = 60;
		var timerInterruptEmail = false;
		
        function setTime(flag, obj) {
        	var countdown = flag===1?countdownPhone:countdownEmail;
        	var timerInterrupt = flag===1?timerInterruptPhone:timerInterruptEmail;
        	
            if (countdown == 0 || timerInterrupt) {
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
            });
		}
		
		function user_update(formObjJsonStr) {

			var indexLayer = layer.load();
			$.ajax({
				url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/updateSelfCenterInfo")),
				type : 'POST', // GET
				async : false, // 或false,是否异步
				data : {
					v : formObjJsonStr
				},
				timeout : 5000, // 超时时间
				dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
				beforeSend : function(xhr) {
					
				},
				success : function(data, textStatus, jqXHR) {

					if (data.hasSuc) {

						layer.confirm('更新成功', {
							icon : 3,
							title : '提示'
						}, function(index) {
							// do something
							window.location = cxt + '/jf/puresport/pagesController/selfcenter';
							layer.close(index);
						});
					} else {
						var tips = data.tipStrings.join("  ");
						layer.alert('更新失败:' + tips, {
							skin : 'layui-layer-molv' ,
							closeBtn : 0
						});
					}
				},
				error : function(xhr, textStatus) {
					console.log('错误', xhr, textStatus);
					layer.alert('系统错误，请联系管理员');
				},
				complete:function(){
					layer.close(indexLayer);
				}
			})
		}
		
		// 设置省份数据
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

	    function setCityAjax(provinceName, defSelectValue) {
	    	$.ajax({
                url:encodeURI(encodeURI(cxt + "/jf/puresport/area/fetchCities")),
                data:{provinceName:provinceName},
                dataType:"json",
                type:"get",
                timeout:5000,   
                async : true,
                cache : true,
                success:function(cityList){

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
        	        if(defSelectValue){
        	        	$city.val(defSelectValue);
        	        }
        	        form.render('select');
                },
                error:function(){
                	layer.msg("获取信息失败");
                }
            })
	    }


});// end $function

function testScoreTableData(){
	var jsonData = {
		"flag": true,
		"itemlist": [{
			"file_path": "",
			"examid": "6",
			"usrid": "204896",
			"tms": "2019-12-16 13:20:20.0",
			"exam_grd": "95",
			"exam_name": "十四冬会",
			"type": "4"
		}, {
			"file_path": "",
			"examid": "2",
			"usrid": "204896",
			"tms": "2019-12-16 13:20:20.0",
			"exam_grd": "95",
			"exam_name": "冬青奥会",
			"type": "6"
		}]
	};
	
	return jsonData;
}

function fetchIntegralData(rowData, funAppendToChild, funTableStrGenerator){
	if(selfCenterTest){
		var tableStr = funTableStrGenerator({
			"exam_grd_category_list": [{
				"file_path": "",
				"examid": "6",
				"usrid": "204896",
				"tms": "2019-12-16 13:20:20.0",
				"exam_grd": "95",
				"exam_name": "十四冬会",
				"type": "4",
				category:'科目1'
			}, {
				"file_path": "20",
				"examid": "2",
				"usrid": "204896",
				"tms": "2019-12-16 13:20:20.0",
				"exam_grd": "95",
				"exam_name": "冬青奥会",
				"type": "6",
				category:'科目2'
			}]
		});
		funAppendToChild(tableStr);
		return ;
	}
	
	$.ajax({
        url:encodeURI(encodeURI(cxt + "/jf/puresport/T11ExamStat/get_exam_grd_category")),
        data:{type:'4'},
        dataType:"json",
        type:"get",
        timeout:5000,   
        async : false,
        cache : false,
        success:function(data){
        	var tableStr = funTableStrGenerator(data);
        	funAppendToChild(tableStr);
        },
        error:function(){
        	layer.msg("获取信息失败");
        }
    })
}

// 查看试卷
function createPaperLink(rowData){
	return '<a href="/jf/puresport/t7Crcl/queryTestPaper?exam_grd=' + rowData.exam_grd
			+ '&tms=' + rowData.tms + '&usrid=' + rowData.usrid + '&type=' + rowData.type + '&examid=' + rowData.examid
			+ '" target="_blank"  role="button"> <code class="text-success bg-success">答题情况</code></a>';
}

// 生成证书 0 5 3
function createCertificate(row){
	var isHasCreditFlag = 1;
	if(row.file_path==null || row.file_path=="")
		isHasCreditFlag = 0;
	if(row.type==null || row.type=="")
		isHasCreditFlag = 1;
	if(isHasCreditFlag==0 && parseInt(row.exam_grd) >= 80 && row.exam_st=="3"){
		return '<a href="/jf/puresport/t7Crcl/generateCredit?totalScore=' + row.exam_grd
		+ '&which_competition_cd=' + row.type
		+ '&examid=' + row.examid
		+ '" target="_blank"  role="button"><code class="text-success bg-success">生成</code>'
		+ '</a>';
	} else {
		return "";
	}
}

function integralFormat(rowData, funAppendToChild){
	console.log('integralFormat', rowData);
	if(rowData.type!='4'){
		funAppendToChild('"该课程没有子科目"');
		return ;
	}
	fetchIntegralData(rowData, funAppendToChild, function(integralData){
		var strTable = '<table cellpadding="5" cellspacing="0" border="1" style="padding-left:50px;width: 100%;">';
		var list = integralData.exam_grd_category_list;
		if(list && list.length > 0){
			strTable += '<tr><td>成绩</td><td>科目</td><td>考试时间</td><td>答题情况</td></tr>';
			for(var i = 0; i< list.length; i++){
				var strRow = '<tr><td>'+list[i].exam_grd +'</td><td>'
										+list[i].category+'</td><td>'
										+list[i].tms + '</td><td>' 
										+createPaperLink(list[i])+'</td></tr>';
				strTable += strRow;
			}
		}else {
			strTable += '<tr><td>没有数据</td></tr>';
		}
		strTable += '</table>';
		
		return strTable;
	});
	
}

function initScoreTable(userID) {
	// alert(userID);
	if (userID) {
		$.ajax({
					url : '/jf/puresport/T11ExamStat/get_exam_grd',
					type : 'POST', // GET
					async : true, // 或false,是否异步
					data : {
						userID : userID
					},
					timeout : 5000, // 超时时间
					dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
					beforeSend : function(xhr) {
						console.log('发送前')
					},
					success : function(data, textStatus, jqXHR) {
						
						if (selfCenterTest){
							// mock数据
							data = testScoreTableData();
						}
						
						if (data.flag) {
							var dataSet = data.itemlist;
							var scoreTable = $('#score_excel').DataTable({
								data : dataSet,
								language : {
									url : "/ui/DataTables/Chinese.json"
								},
								// "filter": false,
								// "destroy": true,
								columns : [
										{
							                "className":      'details-control',
							                "orderable":      false,
							                "data":           null,
							                "defaultContent": ''
							            },
										{
											title : "成绩",
											data: 'exam_grd',
										},
										{
											title : "赛事",
											data: 'exam_name',
										},
										{
											title : "时间",
											data: 'tms',
										},
										{
											title : "查看试卷",
											sortable : false,
											render : function(data, type,row) {
												if(row.type == '4'){
													// 有下一级 则不显示查看试卷
													return '';
												}
												return createPaperLink(row);
											}
										}, {
											title : "生成证书",
											sortable : false,
											render : function(data, type,row) {
												return createCertificate(row);
											}
										} ]
							});// end $('#score_excel').DataTable
							
							// Add event listener for opening and closing details
							// see https://datatables.net/examples/api/row_details.html
						    $('#score_excel tbody').on('click', 'td.details-control', function () {
						        var tr = $(this).closest('tr');
						        var row = scoreTable.row( tr );
						        var rowData = row.data();
						        
						        if ( row.child.isShown() ) {
						            // This row is already open - close it
						            row.child.hide();
						            tr.removeClass('shown');
						        }
						        else {
						        	integralFormat(rowData, function(tableStr){
						        		row.child(tableStr).show();
						        		tr.addClass('shown');
						        	});  
						        }
						    } );
						    
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
