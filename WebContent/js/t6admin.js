$(document).ready(function() {
	var editor = new $.fn.dataTable.Editor({
		ajax : {
			create : {
				type : 'POST',
				url :encodeURI(encodeURI(cxt + "/jf/puresport/t6MgrAhr/addTable"))
			},
			edit : {
				type : 'POST',
				url : encodeURI(encodeURI(cxt + "/jf/puresport/t6MgrAhr/editTable"))
			}
		},
		table : "#example1",
		idSrc:  'usrid',
		fields : [ {
			label : "姓名:",
			name : "nm"
		}, {
			label : "证件类型:",
			name : "crdt_tp",
			type:  "select",
            options: [
                { label: "身份证", value: '身份证' },
                { label: "军官证",  value: '军官证' }
            ],
            def: 0
		}, {
			label : "证件号:",
			name : "crdt_no"
		}, {
			label : "性别:",
			name : "gnd",
			type:  "select",
            options: [
                { label: "男", value: '男' },
                { label: "女",  value: '女' }
            ],
            def: 0
		}, {
			label : "出生日期:",
			name : "brth_dt",
			type: "date"
			
		}, {
			label : "工作单位:",
			name : "wrk_unit",
		}, {
			label : "职务:",
			name : "post",
		}, {
			label : "级别:",
			name : "typeleve",
		}, {
			label : "所属省（自治区）:",
			name : "province",
		}, {
			label : "所属市:",
			name : "city",
		}, {
			label : "所属协会:",
			name : "institute",
		}, {
			label : "手机号:",
			name : "mblph_no",
		}, {
			label : "邮箱:",
			name : "email",
		} ],
		formOptions : {
			bubble : {
				title : '编辑',
				buttons : false
			}
		}
	});
	// 获取查询参数
	var datasrch = {
		id : '',
		name1 : '',
		name2 : '',
		name3 : '',
		pageIndex : '',
		pageSize : ''
	};
	var myTable = $('#example1').DataTable({
		dom : 'Bfrtip',
		select : true,
		serverSide : true,
		ajax : {
			type : "POST",
			url : encodeURI(encodeURI(cxt + "/jf/puresport/t6MgrAhr/getData")),
			data : function ( d ) {
				d.columns=null;
				d.v = JSON.stringify(datasrch);
            }
		},
		buttons : [ {
			extend : 'create',
			text : '添加',
			editor : editor
		}, {
			extend : 'edit',
			text : '编辑',
			editor : editor
		}, {
			extend : 'collection',
			text : '导出',
			buttons : [ 'excel', 'print' ]
		} ],
		
		columns : [  {
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
			data : "wrk_unit"
		}, {
			data : "post"
		}, {
			data : "typeleve"
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
		} ],
		"bProcessing" : true, // DataTables载入数据时，是否显示‘进度’提示
		"sProcessing" : "加载中...",
		"scrollX" : true
	});

	
	function search(data, callback, settings) {
		console.log("search");
//		datasrch.userId = $('#userId').val();
//		datasrch.dateTimeBeg = $('#datetimeBeg').val();
//		datasrch.dateTimeEnd = $('#datetimeEnd').val();
		datasrch.pageIndex = 0;
		datasrch.pageSize = 200;
		// 发送查询请求
		$.ajax({
			type : "get",
			url : encodeURI(encodeURI(cxt + "/jf/puresport/t6MgrAhr/getData")),
			data : {
				v : JSON.stringify(datasrch)
			},
			dataType : 'json',
			contentType : "application/json",
			success : function(response) {
				console.log(response);
				myTable.clear().draw();
				myTable.rows.add(response).draw();
			}
		});
	};
//	$('#btnSearch').click(search);
//	$('#btnSearch').click();
	
	//------------------上传   start     
	function checktext(f) {
	
		if (f == "") {
			alert("请上传excel");
			return false;
		} else if ((!/\.(xls)$/.test(f)) && (!/\.(xlsx)$/.test(f))) {
			alert("文件类型必须是xls或xlsx格式")
			return false;
		} else {
			return true;
		}
	}
	
	$("#inputfileadmin").on('change keyup', function () {
		var f = document.getElementById("inputfileadmin").value;//获取input type="file"的值  
		var docObj = document.getElementById("inputfileadmin");
		uploadtext(f, docObj);
	});
	
	function uploadtext(file,docObj) {
		if (!checktext(file)) {
			return;
		}
		var fileObj = docObj.files[0]; // 获取文件对象  
		var FileController = cxt +"/jf/puresport/t6MgrAhr/inload/";// 接收上传文件的后台地址   
		var form = new FormData();//form 对象  
		form.append("fileexcel", fileObj);// 文件对象  
		$.ajax({
			type : "POST",
			url : FileController,
			data : form,
			processData : false,
			contentType : false,
			success : function(data) {
				console.log(data);
				if(data=="1"){
					alert("上传成功");	
					search("","","");
				}else{
					alert("上传失败，请检查如下信息："+data);
				}
			}
		});
	}
	//------------------上传 end
	//search("","","");
});
