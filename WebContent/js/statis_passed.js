$(document).ready(function() {
	var myTable = $('#example4').DataTable({
		buttons : [ {
			extend : 'collection',
			text : '导出',
			buttons : [ 'excel', 'print' ]
		}  ],
		columns : [  {
			data : "spt_prj"
		}, {
			data : "province"
		},{
			data : "city"
		}, {
			data : "institute"
		}, {
			data : "answered"
		}, {
			data : "passed"
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
			url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/getDataPrjStatis")),
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
	search("","","");
});
