$(document).ready(function() {
	
	// 获取省
	function initProvince() {
		// 发送查询请求
		$.ajax({
			type : "get",
			url : encodeURI(encodeURI(cxt + "/jf/puresport/area/selectProvince")),
			success : function(obj) {
				$("#provSelect_score option:not(:first)").remove();
				$.each(obj,function (index,item) {
					$("#provSelect_score").append("<option value='"+obj[index].id+"'>"+obj[index].name+"</option>");
				});
			}
		});
	};
	// 获取市
	$("#provSelect_score").change(function () {
        var provinceId=$("#provSelect_score option:selected").val();
        $("#citySelect_score option:not(:first)").remove();
        $.ajax({
            url:encodeURI(encodeURI(cxt + "/jf/puresport/area/selectCity")),
            type:"get",
            data:"provinceId="+provinceId,
            success:function (obj) {
                $.each(obj,function (index,item) {
$("#citySelect_score").append("<option value='"+obj[index].id+"'>"+obj[index].name+"</option>");
                });
            } 
        }) 
    });
	
	// 获取协会
	function initInstitute() {
		// 发送查询请求
		$.ajax({
			type : "get",
			url : encodeURI(encodeURI(cxt + "/jf/puresport/area/selectInstitute")),
			success : function(obj) {
				$("#instituteSelect_score option:not(:first)").remove();
				$.each(obj,function (index,item) {
					$("#instituteSelect_score").append("<option value='"+obj[index].institute+"'>"+obj[index].institute+"</option>");
				});
			}
		});
	};
	initProvince();
	
	// =================================
	// 查询参数
	var datasrch = {
		id : '',
		name1 : '',
		name2 : '',
		name3 : '',
		pageIndex : '',
		pageSize : ''
	};
	function getSearchParam() {
		datasrch.name1 = $("#provSelect_score option:selected").html();
		datasrch.name2 = $("#citySelect_score option:selected").html();
		datasrch.name3 = $("#instituteSelect_score option:selected").html();
	};
	var myTable = $('#example3').DataTable({
		dom: 'Bfrtip',
		select : false,
		serverSide : true,
		scrollY : 400,
		scrollX : true,
		responsive : true,
		"searching": true,
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
			url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/getDataScore")),
			data : function ( d ) {
				d.columns=null;
				getSearchParam();
				d.v = JSON.stringify(datasrch);
            }
		},buttons : [{
			extend : 'collection',
			text : '导出',
			buttons : [ 'excel']
			//buttons : [ 'excel', 'print' ]
		}  ],
		columns : [  {
			data : "usr_tp"
		}, {
			data : "spt_prj"
		},{
			data : "nm"
		}, {
			data : "exam_nm"
		}, {
			data : "exam_grd"
		}, {
			data : "passed"
		} , {
			data : "crdt_tp"
		}, {
			data : "crdt_no"
		}, {
			data : "gnd"
		}, {
			data : "brth_dt"
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
		}],
		"columnDefs" : [ {
			"targets" : [ 7 ], // 目标列位置，下标从0开始
			"data" : "crdt_no", // 数据列名
			"render" : function(data, type, full) { // 返回自定义内容
				return "\u200C" + data ; 
			}
		} ]
	});

	// 查询按钮
	$("#selectBtn_score").click(function() {
		// 重新加载table数据
		myTable.ajax.reload();
    });
	
	tableScore = myTable;
});
