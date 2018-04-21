$(document).ready(function() {
	var myTable = $('#example2').DataTable({
		buttons : [ 'excel', 'pdf' ],
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
		} ],
		"bProcessing" : true, // DataTables载入数据时，是否显示‘进度’提示
		"sProcessing" : "loading...",
		"scrollX" : true
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
			url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/getData")),
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

	$("#inputfilesporter").on('change keyup', function () {
		console.log(" inputfilesporter change");
		var f = document.getElementById("inputfilesporter").value;//获取input type="file"的值  
		console.log(f);
		var docObj = document.getElementById("inputfilesporter");
		uploadtext(f, docObj);
	});
	function uploadtext(file,docObj) {
		if (!checktext(file)) {
			return;
		}
		var fileObj = docObj.files[0]; // 获取文件对象  
		var FileController = cxt +"/jf/puresport/t1usrBsc/inload/";// 接收上传文件的后台地址   
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
	search("","","");
});