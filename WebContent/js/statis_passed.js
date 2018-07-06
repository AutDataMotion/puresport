$(document).ready(function() {

	// 获取省
	function initProvince() {
		// 发送查询请求
		$.ajax({
			type : "get",
			url : encodeURI(encodeURI(cxt + "/jf/puresport/area/selectProvince")),
			success : function(obj) {
				$("#provSelect_prj option:not(:first)").remove();
				$.each(obj,function (index,item) {
					$("#provSelect_prj").append("<option value='"+obj[index].id+"'>"+obj[index].name+"</option>");
				});
			}
		});
	};
	// 获取市
	$("#provSelect_prj").change(function () {
        var provinceId=$("#provSelect_prj option:selected").val();
        $("#citySelect_prj option:not(:first)").remove();
        $.ajax({
            url:encodeURI(encodeURI(cxt + "/jf/puresport/area/selectCity")),
            type:"get",
            data:"provinceId="+provinceId,
            success:function (obj) {
                $.each(obj,function (index,item) {
$("#citySelect_prj").append("<option value='"+obj[index].id+"'>"+obj[index].name+"</option>");
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
				$("#instituteSelect_prj option:not(:first)").remove();
				$.each(obj,function (index,item) {
					$("#instituteSelect_prj").append("<option value='"+obj[index].institute+"'>"+obj[index].institute+"</option>");
				});
			}
		});
	};
	
	// 获取项目
	function initProject() {
		// 发送查询请求
		$.ajax({
			type : "get",
			url : encodeURI(encodeURI(cxt + "/jf/puresport/area/selectProject")),
			success : function(obj) {
				$("#prjSelect_prj option:not(:first)").remove();
				$.each(obj,function (index,item) {
					$("#prjSelect_prj").append("<option value='"+obj[index].spt_prj+"'>"+obj[index].spt_prj+"</option>");
				});
			}
		});
	};
	
	initProvince();
	// initInstitute();
	initProject();
	
	// =========================

	// 获取查询参数
	var datasrch = {
		id : '',
		name1 : '',
		name2 : '',
		name3 : '',
		pageIndex : '',
		pageSize : ''
	};
	function getSearchParam() {
		datasrch.name1 = $("#provSelect_prj option:selected").html();
		datasrch.name2 = $("#citySelect_prj option:selected").html();
		//datasrch.name3 = $("#instituteSelect_prj option:selected").html();
		datasrch.name7 = $("#prjSelect_prj option:selected").html();
	};
	
	
	var myTable = $('#example4').DataTable({
		dom: 'Bfrtip',
       select : false,
       serverSide : true,
		scrollY : 400,
		scrollX : true,
		responsive : true,
		"searching": false,
		lengthMenu: [
            [ 10, 100, 1000, 5000,10000 ],
            [ '10 行', '100 行', '1000 行', '5000行' , '10000行']
        ],
		"bProcessing" : true, // DataTables载入数据时，是否显示‘进度’提示
		"sProcessing" : "加载中...",
//		"bFilter" : true, // 过滤功能
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : true, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
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
			url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/getDataPrjStatis")),
			data : function ( d ) {
				d.columns=null;
				getSearchParam();
				d.v = JSON.stringify(datasrch);
            }
		},buttons: [{
			extend : 'pageLength',
			text : '每页行数'
		},{
                extend: 'collection',
                text: '导出',
                buttons : [ 'excel']
            }
       ],
		columns : [  {
			data : "spt_prj"
		}, {
			data : "province"
		},{
			data : "city"
		}, {
			data : "passed"
		} , {
			data : "answered"
		}]
	});

	
	// 查询按钮
	$("#selectBtn_prj").click(function() {
		// 重新加载table数据
		myTable.ajax.reload();
    });
	
	tablePassed = myTable;
	
});
