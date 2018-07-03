$(document).ready(
		function() {

			// 获取试题类型
			function initQuestionType() {
				// 发送查询请求e
				$.ajax({
					type : "get",
					url : encodeURI(encodeURI(cxt
							+ "/jf/puresport/area/selectQuestionType")),
					success : function(obj) {
						$("#typeSelect_question option:not(:first)").remove();
						$.each(obj, function(index, item) {
							$("#typeSelect_question").append(
									"<option value='" + obj[index].prblm_tp
											+ "'>" + obj[index].prblm_tp
											+ "</option>");
						});
					}
				});
			};
			// initQuestionType();
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
				datasrch.name1 = $("#typeSelect_question option:selected").val();
			};

			var myTable = $('#example5').DataTable({
				dom : 'Bfrtip',
				select : true,
				serverSide : true,
				scrollY : 400,
				scrollX : true,
				responsive : true,
				"searching" : true,
				"bProcessing" : true, // DataTables载入数据时，是否显示‘进度’提示
				"sProcessing" : "加载中...",
				// "bFilter" : true, // 过滤功能
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
					url : encodeURI(encodeURI(cxt + "/jf/puresport/t1usrBsc/getDataExamQues")),
					data : function ( d ) {
						d.columns=null;
						getSearchParam();
						d.v = JSON.stringify(datasrch);
		            }
				},buttons : [ {
					extend : 'collection',
					text : '导出',
					buttons : [ 'excel' ]
				} ],
				columns : [ {
					data : "prblm_tp"
				}, {
					data : "ttl"
				}, {
					data : "errorPercent"
				}, {
					data : "opt"
				}, {
					data : "prblm_aswr"
				}, {
					data : "scor"
				} ],
				"columnDefs" : [ {
					"targets" : [ 0 ], // 目标列位置，下标从0开始
					"data" : "prblm_tp", // 数据列名
					"render" : function(data, type, full) { // 返回自定义内容
						if (data == '01')
							return "选择题";
						if (data == '02')
							return "判断题";
						return "--";
					}
				} ]
			});

			// 查询按钮
			$("#selectBtn_question").click(function() {
				// 重新加载table数据
				myTable.ajax.reload();
			});
			
			tableQuestion = myTable;
		});
