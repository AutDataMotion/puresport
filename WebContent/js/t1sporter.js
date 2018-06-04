$(document).ready(function() {
	// js 数据module
	var dataMdl = {
		id : '',
		nm : '',
		crdt_tp : '',
		crdt_no : '',
		gnd : '',
		brth_dt : '',
		spt_prj : '',
		typeleve : '',
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
			nm : $(':text[name="t1usrBsc.nm"]'),
			crdt_tp : $(':text[name="t1usrBsc.crdt_tp"]'),
			crdt_no : $(':text[name="t1usrBsc.crdt_no"]'),
			gnd : $(':text[name="t1usrBsc.gnd"]'),
			brth_dt : $(':text[name="t1usrBsc.brth_dt"]'),
			spt_prj : $(':text[name="t1usrBsc.spt_prj"]'),
			typeleve : $(':text[name="t1usrBsc.typeleve"]'),
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
		name1 : '',
		name2 : '',
		name3 : '',
		pageIndex : '',
		pageSize : ''
	};
	
	var tableRowSelect = null;
	var tableBtnType = 1;// 1:添加 2:编辑
	var myTable = $('#example2').DataTable({
		dom : 'Bfrtip',
		select : true,
		serverSide : true,
		scrollY : 400,
		scrollX : true,
		responsive : true,
		search : false,
		"bProcessing" : true, // DataTables载入数据时，是否显示‘进度’提示
		"sProcessing" : "加载中...",
		"bFilter" : false, // 过滤功能
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : true, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : true, // 排序功能
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
		buttons : [ {
			text : '添加',
			action : function(e, dt, node, config) {
				// 清空表单赋值
				selectRowToForm(null);
				// 修改表单提示文字
				$('#exampleModalLabelSporter').text('添加');
				tableBtnType = 1;
				$("#sporterModal").modal('show');
			}
		}, {
			text : '编辑',
			action : function(e, dt, node, config) {
				if (null == tableRowSelect) {
					alert('请先选择某行');
					return;
				}
				// 给表单赋值
				selectRowToForm(tableRowSelect);
				// 修改表单提示文字
				$('#exampleModalLabelSporter').text('修改');
				tableBtnType = 2;
				$("#sporterModal").modal('show');
			}
		}, {
			extend : 'collection',
			text : '导出',
			buttons : [ 'excel', 'print' ]
		} ],
		columns : [ {
			data : "usrid",
			"visible": false
		},{
			data : "nm"
		}, {
			data : "crdt_tp"
		}, {
			data : "crdt_no"
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
			data : "institute"
		}, {
			data : "mblph_no"
		}, {
			data : "email"
		} ]
	});


	// ============选中一行触发
	$('#example2 tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
			tableRowSelect = null;
		} else {
			myTable.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
			tableRowSelect = myTable.row(this).data();
		}
	});

	// ================Form表单操作
	function selectRowToForm(row) {
		var formMdl = curFormMdl();
		if (null === row) {
			formMdl.usrid.val('');
			formMdl.nm.val('');
			formMdl.crdt_tp.val('');
			formMdl.crdt_no.val('');
			formMdl.gnd.val('');
			formMdl.brth_dt.val('');
			formMdl.spt_prj.val('');
			formMdl.typeleve.val('');
			formMdl.province.val('');
			formMdl.city.val('');
			formMdl.institute.val('');
			formMdl.mblph_no.val('');
			formMdl.email.val('');
			return;
		}
		formMdl.usrid.val(row.usrid);
		formMdl.nm.val(row.nm);
		formMdl.crdt_tp.val(row.crdt_tp);
		formMdl.crdt_no.val(row.crdt_no);
		formMdl.gnd.val(row.gnd);
		formMdl.brth_dt.val(row.brth_dt);
		formMdl.spt_prj.val(row.spt_prj);
		formMdl.typeleve.val(row.typeleve);
		formMdl.province.val(row.province);
		formMdl.city.val(row.city);
		formMdl.institute.val(row.institute);
		formMdl.mblph_no.val(row.mblph_no);
		formMdl.email.val(row.email);
	}

	function FormToDataMdl() {
		var formMdl = curFormMdl();
		dataMdl.nm = formMdl.nm.val();
		dataMdl.crdt_tp = formMdl.crdt_tp.val();
		dataMdl.crdt_no = formMdl.crdt_no.val();
		dataMdl.gnd = formMdl.gnd.val();
		dataMdl.brth_dt = formMdl.brth_dt.val();
		dataMdl.spt_prj = formMdl.spt_prj.val();
		dataMdl.typeleve = formMdl.typeleve.val();
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
	function search(data, callback, settings) {
		// datasrch.userId = $('#userId').val();
		// datasrch.dateTimeBeg = $('#datetimeBeg').val();
		// datasrch.dateTimeEnd = $('#datetimeEnd').val();
		datasrch.pageIndex = 0;
		datasrch.pageSize = 200;
		// 发送查询请求
		$.ajax({
			type : "get",
			url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/getData")),
			data : {
				v : JSON.stringify(datasrch)
			},
			dataType : 'json',
			contentType : "application/json",
			success : function(response) {
				myTable.clear().draw();
				myTable.rows.add(response).draw();
			}
		});
	}
	;
	// $('#btnSearch').click(search);
	// $('#btnSearch').click();

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
		console.log(" inputfilesporter change");
		var f = document.getElementById("inputfilesporter").value;// 获取input
																	// type="file"的值
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
				console.log(data);
				if (data == "1") {
					layer.msg("上传成功");
				} else {
					layer.msg("文件处理有点问题，信息如下：" + data);
				}
				search("", "", "");
			}
		});
	}
	// ------------------上传 end
	// search("", "", "");
});
