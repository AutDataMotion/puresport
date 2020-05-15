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
	
	// 获取分组
	function initSporterGroup() {
		// 发送查询请求
		$.ajax({
			type : "get",
			url : encodeURI(encodeURI(cxt + "/jf/puresport/t15Group/fetchGroup")),
			success : function(groups) {
				$("#src_sport_group option:not(:first)").remove();
				var selectGroup = $("#src_sport_group");
				for (var i = 0, len = groups.length; i < len; i++) {
    	            var item = groups[i];
    	            var option = $("<option value='" + item.id + "' name='"+item.title+"'>" + item.title + "</option>");
    	            // 添加到 select 元素中
    	            selectGroup.append(option);
    	        }
			}
		});
	};
	
	initProvince();
	initSporterGroup();
	
	// =================================
	// 查询参数
	var datasrch = {
		id : '',
		name1 : '',
		name2 : '',
		name3 : '',
		name4 : '',
		name5 : '',
		name6 : '',
		name7 : '',
		name8 : '',
		name9 : '',
		name10 : '',
		name11 : '',
		pageIndex : '',
		pageSize : ''
	};
	function getSearchParam() {
		datasrch.name1 = $("#provSelect_score option:selected").html().trim();
		datasrch.name2 = $("#citySelect_score option:selected").html();
		datasrch.name3 = $("#instituteSelect_score option:selected").html();
		
		datasrch.name4 = $("#sta_t1usrBsc_nm").val();
		datasrch.name5 = $("#sta_t1usrBsc_crdt_no").val();
		datasrch.name6 = $("#sta_t1usrBsc_usr_tp option:selected").html();
		datasrch.name7 = $("#sta_t1usrBsc_spt_prj").val();
		
		datasrch.name8 = $("#sta_t1usrBsc_exam_nm option:selected").html();
		// datasrch.name9 = $("#sta_t1usrBsc_exam_grd").val();
		datasrch.name10 = $("#sta_t1usrBsc_passOrnot option:selected").html();
		datasrch.name11 = $("#sta_t1usrBsc_gnd option:selected").html();
		datasrch.name12 = $("#sta_t1usrBsc_examSt option:selected").val();
		datasrch.name13 = $("#src_sport_group option:selected").val();
	};
	var myTable = $('#example3').DataTable({
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
		"bAutoWidth": false,
		"oLanguage" : {
			"sProcessing" : "加载中...",
			"sLoadingRecords" : "载入中...",
			"sInfoEmpty" : "没有数据",
			"sEmptyTable" : "表中数据为空",
			"sZeroRecords" : "没有找到数据",
			"sLengthMenu" : "显示 _MENU_ 条记录",
			"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条",
			"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上页",
				"sNext" : "下页",
				"sLast" : "末页"
			}
		},
		ajax : {
			type : "POST",
			url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/getDataScore")),
			data : function ( d ) {
				d.columns=null;
				getSearchParam();
				d.v = JSON.stringify(datasrch);
				layer.load(1);
        },
        dataSrc: function ( json ) {  
        			layer.closeAll('loading');
                return json.data;  
        }  
		},buttons : [{
			extend : 'pageLength',
			text : '每页行数'
		},{
			extend : 'collection',
			text : '导出',
			buttons : [{
				extend: 'excel',
				 exportOptions: {
					 "columns": [0, 1,2, 3, 4, 5],//设置需要导出的列索引
				 }
			}]
			//buttons : [ 'excel', 'print' ]
		}  ],
		columns : [  /*{
			data : "usr_tp"
		},*/ {
			data : "exam_nm"
		}
		, {
			data : "exam_grd"
		}, {
			data : "passed"
		} 
		,{
			data : "nm"
		}, {
			data : "crdt_no"
		}, {
			data : "spt_prj"
		}, /*{
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
		},*/
		{
			title : "查看试卷",
			sortable : false,
			render : function(
					data, type,
					row) {
				
				return '<a href="/jf/puresport/t7Crcl/queryTestPaper?usrid='
						+ row.usrid
						+ '&examid='
						+ row.examid
						+ '&type='
						+ row.type
						+ '&exam_grd='
						+ row.exam_grd
						+ '&userFlag=1" target="_blank"  role="button">'
						/*+ '<code class="text-success bg-success">答题情况</code>'*/
						+ '<span class="badge">查看</span>'
						+ '</a>';
			}
		}],	
		columnDefs : [ {
			"targets" : [4], // 目标列位置，下标从0开始
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
});
