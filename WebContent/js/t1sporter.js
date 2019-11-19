$(document).ready(function() {
	// js 数据module
	var dataMdl = {
		id : '',
		usr_tp:'',
		nm : '',
		crdt_tp : '',
		crdt_no : '',
		department:'',
		post:'',
		gnd : '',
		brth_dt : '',
		spt_prj : '',
		typelevel : '',
		province : '',
		city : '',
		institute : '',
		mblph_no : '',
		email : ''
	};

	// 表单 引用
	function  curFormMdl() {
		return  {
			usrid : $(':text[name="t1usrBsc.usrid"]'),
			usr_tp : $(':text[name="t1usrBsc.usr_tp"]'),
			nm : $(':text[name="t1usrBsc.nm"]'),
			crdt_tp : $(':text[name="t1usrBsc.crdt_tp"]'),
			crdt_no : $(':text[name="t1usrBsc.crdt_no"]'),
			department : $(':text[name="t1usrBsc.department"]'),
			post : $(':text[name="t1usrBsc.post"]'),
			gnd : $(':text[name="t1usrBsc.gnd"]'),
			brth_dt : $(':text[name="t1usrBsc.brth_dt"]'),
			spt_prj : $(':text[name="t1usrBsc.spt_prj"]'),
			typelevel : $(':text[name="t1usrBsc.typelevel"]'),
			province : $(':text[name="t1usrBsc.province"]'),
			city : $(':text[name="t1usrBsc.city"]'),
			institute : $(':text[name="t1usrBsc.institute"]'),
			mblph_no : $(':text[name="t1usrBsc.mblph_no"]'),
			email : $(':text[name="t1usrBsc.email"]')
		}
	};
	
	// 获取查询参数
	var datasrch = {
		id : '',
		name1 : '',//姓名
		name2 : '',//证件号
		name3 : '',//所属省
		name4 : '',//所属市
		name5 : '',//人员类型
		name6 : '',//运动项目
		name7 : '',//级别
		name8 : '',//性别
		exportall : '',
		pageIndex : '',
		pageSize : ''
	};
	
	function initGroupSelect(){
    	// 获取分组信息
    	$.ajax({
            url:encodeURI(encodeURI(cxt + "/jf/puresport/t15Group/fetchGroup")),
            data:{},
            dataType:"json",
            type:"get",
            timeout:5000,   
            async : true,
            cache : true,
            success:function(groups){
            	console.log('groups', groups)
            	var $sel = $("#selSportGroup");
            	$sel.empty();
    	        // 获取对应省份城市
    	        for (var i = 0, len = groups.length; i < len; i++) {
    	            var item = groups[i];
    	            var option = $("<option value='" + item.id + "' name='"+item.title+"'>" + item.title + "</option>");
    	            // 添加到 select 元素中
    	            $sel.append(option);
    	        }
    	        layui.form.render('select');    	        
            },
            error:function(){
            	layui.layer.msg("获取信息失败");
            }
        })
    }
	
	var tableRowSelect = null;
	var tableBtnType = 1;// 1:添加 2:编辑
	var selectRows = null;
	var myTable = $('#example2').DataTable({
		dom : 'Bfrtip',
		select : {
            style: 'multi'
        },
		serverSide : true,
		scrollY : 400,
		scrollX : true,
		responsive : true,
		"searching": false,
		"ordering": false,
		"bProcessing" : true, // DataTables载入数据时，是否显示‘进度’提示
		"sProcessing" : "加载中...",
//		"bFilter" : true, // 过滤功能
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : true, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bAutoWidth": false,
		"oLanguage" : {
			"sLengthMenu" : "每页显示 _MENU_ 条记录",
			"sZeroRecords" : "抱歉， 没有找到",
			"sInfoEmpty" : "没有数据",
			"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "前一页",
				"sNext" : "后一页",
				"sLast" : "尾页"
			},
			"sZeroRecords" : "没有检索到数据",
		},
		ajax : {
			type : "POST",
			url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/getData")),
			data : function ( d ) {
				d.columns=null;
				d.v = JSON.stringify(datasrch);
            }
		},
		buttons : [ 
//		{
//			text : '添加',
//			action : function(e, dt, node, config) {
//				// 清空表单赋值
//				selectRowToForm(null);
//				// 修改表单提示文字
//				$('#exampleModalLabelSporter').text('添加');
//				tableBtnType = 1;
//				$("#sporterModal").modal('show');
//			}
//		},
			{
				extend : 'pageLength',
				text : '每页行数'
			},
		{
				text : '分组',
				action : function(e, dt, node, config) {
					selectRows = myTable.rows( { selected: true } );
					var count = selectRows.count();
					if (0 === count) {
						layer.msg('请先选择某行');
						return;
					}
					console.log("selectRows data", selectRows.data());
					
					// 获取分组列表
					initGroupSelect();
					
					$("#sportGroupModal").modal('show');
				
				}
		},{
			text : '编辑',
			action : function(e, dt, node, config) {
				var selectRows = myTable.rows( { selected: true } );
				var count = selectRows.count();
				if (count===0) {
					layer.msg('请先选择某一行');
					return;
				} else if (count > 0){
					layer.msg('只能选择一行');
					return;
				}
				tableRowSelect = selectRows.data()[0];
				// 给表单赋值
				selectRowToForm(tableRowSelect);
				// 修改表单提示文字
				$('#exampleModalLabelSporter').text('修改');
				tableBtnType = 2;
				$("#sporterModal").modal('show');
			}
		},{
			text : '删除',
			action : function(e, dt, node, config) {
				if (null == tableRowSelect) {
					layui.layer.msg('请先选择某行');
					return;
				}
				// 确认删除
				layer.confirm('确定删除吗？', {
				  btn: ['删除','取消'] //按钮
				}, function(){
					// 删除操作
					// 获取usrid
					alert(tableRowSelect.usrid);
					var delparam = {
							id : tableRowSelect.usrid,
							name1 : '',
							name2 : '',
							name3 : '',
							pageIndex : '',
							pageSize : ''
						};
					// 发送查询请求
					$.ajax({
						type : "get",
						url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/delSporter")),
						data : {
							v : JSON.stringify(delparam)
						},
						contentType : "application/json",
						success : function(response) {
							layer.msg(response);
							// 重新加载table数据
							myTable.ajax.reload();
						}
					});
				}, function(){
					// 取消操作
				});
			
			}
		}, {
			extend : 'collection',
			text : '导出',
//			buttons : [ 'excel','csv']
			buttons : [
				{ text: 'excel',action: function () {
					datasrch.exportall = '1';
					$.ajax({
					    url:"/jf/puresport/t1usrBsc/getAllData",
					    type:'POST', //GET
//					    async:false,    //或false,是否异步
					    data : {
							v : JSON.stringify(datasrch)
						},
					    timeout:5000,    //超时时间
					    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
					    beforeSend:function(xhr){
					        $("#example2").busyLoad("show", { text: "LOADING ...",
					    		textPosition: "top"
					    	});
					    },
					    success:function(data,textStatus,jqXHR){
					    	datasrch.exportall = '0';
					    	if(data.flag)
					    	{
					    		window.location.href=data.fileUrl;
					    	}
					    	else{
					    		layer.msg("文件下载失败！");
					    	}
					    	
					    },
					    error:function(xhr,textStatus){
					    	datasrch.exportall = '0';
					    	layer.msg("文件下载失败！");
					    },
					    complete:function(){
					    	 $("#example2").busyLoad("hide");
					    }
					})
				} }
//				,
//                { text: 'csv', action: function () {
//                	
//                } }
			]
			//buttons : [ 'excel', 'print' ]
		},{
			text : '取消选中',
			action : function(e, dt, node, config) {
				myTable.rows().deselect();
			}
		}],
		columns : [ {
			data : "usrid",
			"visible": false
		},{
			data : "usr_tp"
		},{
			data : "nm"
		}, {
			data : "crdt_tp"
		}, {
			data : "crdt_no"
		},{
			data : "department"
		},{
			data : "post"
		}, {
			data : "gnd"
		}, {
			data : "brth_dt"
		}, {
			data : "spt_prj"
		}, {
			data : "typelevel"
		}, {
			data : "province"
		}, {
			data : "city"
		}, {
			data : "institute",
			"visible": false
		}, {
			data : "mblph_no"
		}, {
			data : "email"
		} ],
	    columnDefs: [{
	        targets:[4],// 目标列位置，下标从0开始
	        data : "crdt_no", // 数据列名
	        render: function(data,type, full){ // 返回自定义内容
	                return "\u200C" + data ; 
	          }
	     }]
	});

	layui.$('#sportGroupSelect_btn').on('click', function(){
		
		var group = $('#selSportGroup');
		var v = group.val();

		console.log("group", group, "val", v);
		
		var data = {};
		data.id = v;
		data.userIds=[];
		var users = selectRows.data();
		for(var i=0; i< users.length; i++){
			var user = users[i];
			data.userIds.push(user.usrid);
		}
		
		var indexLoading = layer.load();
		
		$.ajax({
            url:encodeURI(encodeURI(cxt + "/jf/puresport/r16GroupUsr/updateUserGroup")),
            data:{v:JSON.stringify(data)},
            dataType:"json",
            type:"post",
            timeout:60000,   
            async : true,
            cache : false,
            success:function(group){
            	if(group.hasSuc){
            		layer.msg('分组成功',{
            			  icon: 1,
            			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
            		}, function(){
            			$("#sportGroupModal").modal('hide');
        			    myTable.rows().deselect();
            		});
                	
            	} else {
            		var tips = data.tipStrings.join("  ");
            		layer.msg(tips);
            	}
            	
            },
            error:function(){
            	layer.msg("操作失败，请稍后重试");
            },
            complete:function(){
            	console.log('complete');
            	layer.close(indexLoading);
            }
        })
        
		
	});// end ui
    
	// ============选中一行触发
//	$('#example2 tbody').on('click', 'tr', function() {
//		if ($(this).hasClass('selected')) {
//			$(this).removeClass('selected');
//			tableRowSelect = null;
//		} else {
//			myTable.$('tr.selected').removeClass('selected');
//			$(this).addClass('selected');
//			tableRowSelect = myTable.row(this).data();
//		}
//	});

	// ================Form表单操作
	function selectRowToForm(row) {
		var formMdl = curFormMdl();
		if (null === row) {
			formMdl.usrid.val('');
			formMdl.usr_tp.val('');
			formMdl.nm.val('');
			formMdl.crdt_tp.val('');
			formMdl.crdt_no.val('');
			formMdl.department.val('');
			formMdl.post.val('');
			formMdl.gnd.val('');
			formMdl.brth_dt.val('');
			formMdl.spt_prj.val('');
			formMdl.typelevel.val('');
			formMdl.province.val('');
			formMdl.city.val('');
			formMdl.institute.val('');
			formMdl.mblph_no.val('');
			formMdl.email.val('');
			return;
		}
		formMdl.usrid.val(row.usrid);
		formMdl.usr_tp.val(row.usr_tp);
		formMdl.nm.val(row.nm);
		formMdl.crdt_tp.val(row.crdt_tp);
		formMdl.crdt_no.val(row.crdt_no);
		formMdl.department.val(row.department);
		formMdl.post.val(row.post);
		formMdl.gnd.val(row.gnd);
		formMdl.brth_dt.val(row.brth_dt);
		formMdl.spt_prj.val(row.spt_prj);
		formMdl.typelevel.val(row.typelevel);
		formMdl.province.val(row.province);
		formMdl.city.val(row.city);
		formMdl.institute.val(row.institute);
		formMdl.mblph_no.val(row.mblph_no);
		formMdl.email.val(row.email);
	}

	function FormToDataMdl() {
		var formMdl = curFormMdl();
		dataMdl.usr_tp = formMdl.usr_tp.val();
		dataMdl.nm = formMdl.nm.val();
		dataMdl.crdt_tp = formMdl.crdt_tp.val();
		dataMdl.crdt_no = formMdl.crdt_no.val();
		dataMdl.department = formMdl.department.val();
		dataMdl.post = formMdl.post.val();
		
		dataMdl.gnd = formMdl.gnd.val();
		dataMdl.brth_dt = formMdl.brth_dt.val();
		dataMdl.spt_prj = formMdl.spt_prj.val();
		dataMdl.typelevel = formMdl.typelevel.val();
		dataMdl.province = formMdl.province.val();
		dataMdl.city = formMdl.city.val();
		dataMdl.institute = formMdl.institute.val();
		dataMdl.mblph_no = formMdl.mblph_no.val();
		dataMdl.email = formMdl.email.val();
	}
	// --------------------------表单提交
	var urlAdd = "/jf/puresport/t1usrBsc/addSporter";
	var urlEdit = "/jf/puresport/t1usrBsc/editSporter";
	var urlUse = null;
	$('#sporterModal_btn').bind('click', function() {
		if (tableBtnType == 1) {
			urlUse = urlAdd;
		} else if (tableBtnType == 2) {
			urlUse = urlEdit;
		}
		// 发送查询请求
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			dataType : "json",
			url : encodeURI(encodeURI(cxt + urlUse)),
			data :$("#form_sporter").serialize(),
			success : function(response) {
				// 操作结果提示
				layer.msg(response.status.name);
				// 隐藏form表单
				$("#sporterModal").modal('hide');
				// 重新加载table数据
				myTable.ajax.reload();
			}
		});
	});
	
	// =============搜索查询	
	 $('#s_btn_spt').click( function() {
//		 alert($('#s_t1usrBsc_crdt_no').val());
		 datasrch.name1 = $('#s_t1usrBsc_nm').val();
		 datasrch.name2 = $('#s_t1usrBsc_crdt_no').val();
		 datasrch.name3 = $('#s_t1usrBsc_province').val();
		 datasrch.name4 = $('#s_t1usrBsc_city').val();
		 datasrch.name5 = $('#s_t1usrBsc_usr_tp').val();
		 datasrch.name6 = $('#s_t1usrBsc_spt_prj').val();
		 datasrch.name7 = $('#s_t1usrBsc_typelevel').val();
		 datasrch.name8 = $('#s_t1usrBsc_gnd').val();
		// 重新加载table数据
		myTable.ajax.reload();
	 });

	// ------------------上传 start
	function checktext(f) {
		if (f == "") {
			layer.msg("请上传excel");
			return false;
		} else if ((!/\.(xls)$/.test(f)) && (!/\.(xlsx)$/.test(f))) {
			layer.msg("文件类型必须是xls或xlsx格式")
			return false;
		} 
		return true;
	}

	$("#inputfilesporter").on('change', function() {
		var f = document.getElementById("inputfilesporter").value;// 获取input type="file"的值
		var docObj = document.getElementById("inputfilesporter");
		uploadtext(f, docObj);
	});
	
	function uploadtext(file, docObj) {
		if (!checktext(file)) {
			return;
		}
		var fileObj = docObj.files[0]; // 获取文件对象
		var FileController = cxt + "/jf/puresport/t1usrBsc/inload/";// 接收上传文件的后台地址
		var form = new FormData();// form 对象
		form.append("fileexcel", fileObj);// 文件对象
		$.ajax({
			type : "POST",
			url : FileController, 
			data : form,
			processData : false,
			contentType : false,
			success : function(data) {
				//并且清空原文件，不然选择相同文件不能再次传 
			     $('#inputfilesporter').val(''); 
			     // 重新加载table数据
				myTable.ajax.reload();
				if (data== "1") {
					layer.msg("上传成功");
				} else {
					layer.alert(data);
				}
				
			}
		});
	}
	// ------------------上传 end
});
