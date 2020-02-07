// depend on  layui.js


function ajaxCommon(getOrPost, url, dataJson, funSucc, funFail){
	// 通用Ajax
	$.ajax({
		url : encodeURI(encodeURI(cxt + url)),
		type : getOrPost, // GET
		async : true, // 或false,是否异步
		data : dataJson,
		timeout : 5000, // 超时时间
		dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
		success : function(data, textStatus, jqXHR) {
			if(data.code==undefined || data.code != '0000'){
				// fail
				layer.confirm('抱歉，系统开小差了，请稍后重试！', {
					icon : 3,
					title : '系统提示'
				}, function(index) {
					typeof funFail === "function" ? funFail() : false;
					layer.close(index);
				});
				console.log(data.desc);
			}else{
				// success
				typeof funSucc === "function" ? funSucc(data) : false;
			}
		},
		error : function(xhr, textStatus) {
			layer.msg('抱歉，系统开小差了，请稍后重试！');
		}
	})
}

function ajaxGet(url, dataJson, funSucc, funFail){
	// Ajax Get
	ajaxCommon('GET', url, dataJson, funSucc, funFail);
}

function ajaxPost(url, dataJson, funSucc, funFail){
	// Ajax Get
	ajaxCommon('POST', url, dataJson, funSucc, funFail);
}



var jifenDataModel = {
		usrid:0,
		exam_grd:0,
		category_status_list:[],
		
};
function getAllScore(){
	// 查询积分制准入学习总成绩
}


function getCourseList(){
	// 查询积分制课程准入学习情况
}

function getBonus(){
	// 查询积分制附加题学习情况
}

function getAllScoreAjax(){
	// 查询积分制准入学习总成绩
	url = '/jf/puresport/t7Crcl/query_score_05';
	dataJson = {
			type: 4
	};
	funSucc = function(data){
		jifenDataModel.usrid = data.usrid;
		jifenDataModel.exam_grd = data.exam_grd;
		$("#score").css("width",jifenDataModel.exam_grd+"%");
	}
	
	funFail = function(){
		
	}
	
	ajaxGet(url, dataJson, funSucc, funFail);
}


function getCourseUrl(id){
	return cxt + '/jf/puresport/t7Crcl/study_notify_tokyo_1?which_competition=4&category=' + id;
}

var studyStatusMap = {
		'1':{
			name:'未考试', 
			cssAble:'',
			funUrl: function(id){
					return getCourseUrl(id);
				},
			fun:function(){
				
			}
		},
		'2':{
			name:'已考试',
			cssAble:'',
			funUrl: function(id){
				return getCourseUrl(id);
			},
			fun:function(){
				
			}
		},
		'3':{
			name:'未解锁', 
			cssAble:'disable',
			funUrl: function(id){
				return 'javascript:;';
			},
			fun:function(){
				
			}
		},
		'4':{
			name:'未上线', 
			cssAble:'disable',
			funUrl: function(id){
				return 'javascript:;';
			},
			fun:function(){
				
			}
		},
};
function setStudyStatus(id, statusId){
	var catDiv = $('#cat'+id);
	var statusDiv = $('#cat-status'+id);
	var statusFun = studyStatusMap[statusId];
	
	catDiv.attr('href',statusFun.funUrl(id));
	catDiv.addClass(statusFun.cssAble);
	statusDiv.html(statusFun.name);
}
function getCourseListAjax(){
	// 查询积分制课程准入学习情况
	url = '/jf/puresport/t7Crcl/query_category_status_05';
	dataJson = {
			type: 4
	};
	funSucc = function(data){
		jifenDataModel.category_status_list = data.category_status_list;
		catList = jifenDataModel.category_status_list;
		for(var i=0; i< catList.length; i++){
			// category 1~6 对应
			setStudyStatus(catList[i].category, catList[i].stdy_st);
		}
	}
	
	funFail = function(){
		
	}
	
	ajaxGet(url, dataJson, funSucc, funFail);
}

function getBonusAjax(){
	// 查询积分制附加题学习情况
	url = '/jf/puresport/t7Crcl/query_bonus_status_05';
	dataJson = {
			type: 4
	};
	funSucc = function(data){
		
	}
	
	funFail = function(){
		
	}
	
	ajaxGet(url, dataJson, funSucc, funFail);
}


function initAll(){
	// 初始化页面
	var indexLoading = layer.load();
	
	getAllScoreAjax();
	getCourseListAjax();
	getBonusAjax();
	
	layer.close(indexLoading);
}

initAll();
