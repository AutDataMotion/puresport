// depend on  layui.js

jifenTest = true;

var testDataScore = {
		"code": "0000",
		"usrid": 444,
		"exam_grd": 80,
	};


var testDataCategory = {
		"code": "0000",
		"usrid": 444,
		"category_status_list": [{
			"crcl_nm": "预防误服误用       ",
			"stdy_st": "1",
			"category": "1"
		}, {
			"crcl_nm": "预防误服误用       ",
			"stdy_st": "2",
			"category": "2"
		}, {
			"crcl_nm": "预防误服误用       ",
			"stdy_st": "3",
			"category": "3"
		}, {
			"crcl_nm": "预防误服误用       ",
			"stdy_st": "3",
			"category": "4"
		}, {
			"crcl_nm": "预防误服误用       ",
			"stdy_st": "4",
			"category": "5"
		}, {
			"crcl_nm": "预防误服误用       ",
			"stdy_st": "4",
			"category": "6"
		}],
		"desc": "查询成功"
	};

var testDataBonus = {
		"code": "0000",
		"usrid": 444,
		"bonus_status_list": [{
			"score": '1',
			"usrid": "3",
			"category": "1"
		}, {
			"score": '0',
			"usrid": "3",
			"category": "2"
		}, {
			"score": '1',
			"usrid": "3",
			"stdy_st": "3",
			"category": "3"
		}, {
			"score": '0',
			"usrid": "3",
			"category": "4"
		}, {
			"score": '1',
			"usrid": "3",
			"category": "5"
		}, {
			"score": '0',
			"usrid": "3",
			"category": "6"
		}, {
			"score": '1',
			"usrid": "3",
			"category": "7"
		}, {
			"score": '0',
			"usrid": "3",
			"category": "8"
		}, {
			"score": '1',
			"usrid": "3",
			"category": "9"
		}, {
			"score": '0',
			"usrid": "3",
			"category": "10"
		}],
		"desc": "查询成功"
	};

function ajaxCommonFunSuc(data, funSucc, funFail){
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
}

function ajaxCommon(getOrPost, url, dataJson, funSucc, funFail, rspTestData){
	
	if(jifenTest){
		ajaxCommonFunSuc(rspTestData, funSucc, funFail);
		return;
	}
	// 通用Ajax
	$.ajax({
		url : encodeURI(encodeURI(cxt + url)),
		type : getOrPost, // GET
		async : true, // 或false,是否异步
		data : dataJson,
		timeout : 5000, // 超时时间
		dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
		success : function(data, textStatus, jqXHR) {
			ajaxCommonFunSuc(data, funSucc, funFail);
		},
		error : function(xhr, textStatus) {
			layer.msg('抱歉，系统开小差了，请稍后重试！');
		}
	})
}

function ajaxGet(url, dataJson, funSucc, funFail, rspTestData){
	// Ajax Get
	ajaxCommon('GET', url, dataJson, funSucc, funFail, rspTestData);
}

function ajaxPost(url, dataJson, funSucc, funFail, rspTestData){
	// Ajax Get
	ajaxCommon('POST', url, dataJson, funSucc, funFail, rspTestData);
}



var jifenDataModel = {
		usrid:0,
		exam_grd:0,
		category_status_list:[],
		bonus_status_list:[]
};


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
	
	ajaxGet(url, dataJson, funSucc, funFail, testDataScore);
}


function getCourseUrl(id){
	return cxt + '/jf/puresport/t7Crcl/study_notify_tokyo_1?which_competition=4&category=' + id;
}

var studyStatusMap = {
		'1':{
			name:'未考试', 
			cssAble:'',
			target:'blank',
			funUrl: function(id){
					return getCourseUrl(id);
				},
		},
		'2':{
			name:'已考试',
			cssAble:'',
			target:'blank',
			funUrl: function(id){
				return getCourseUrl(id);
			},
		},
		'3':{
			name:'未解锁', 
			cssAble:'disable',
			target:'',
			funUrl: function(id){
				return 'javascript:;';
			},
		},
		'4':{
			name:'未上线', 
			cssAble:'disable',
			target:'',
			funUrl: function(id){
				return 'javascript:;';
			},
		},
};
function setStudyStatus(id, statusId){
	var catDiv = $('#cat'+id);
	var statusDiv = $('#cat-status'+id);
	var statusFun = studyStatusMap[statusId];
	
	catDiv.attr('href',statusFun.funUrl(id));
	catDiv.attr('target',statusFun.target);
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
	
	ajaxGet(url, dataJson, funSucc, funFail, testDataCategory);
}

function getBonusUrl(id){
	return cxt + '/jf/puresport/t7Crcl/study_notify_tokyo_1?which_competition=4&category=' + id;
}
var bonusStatusMap = {
		'0':{
			name:'未学习', 
			cssAble:'disable',
			target:'blank',
			funUrl: function(id){
					return getBonusUrl(id);
			},
		},
		'1':{
			name:'已学习',
			cssAble:'',
			target:'blank',
			funUrl: function(id){
				return getBonusUrl(id);
			},
		},
};
function setBonusStatus(id, score){
	console.log('setBonusStatus', id, score);
	var imgDiv = $('#yun'+id);
	var statusFun = bonusStatusMap[score];
	
	imgDiv.attr('href',statusFun.funUrl(id));
	imgDiv.attr('target',statusFun.target);
	imgDiv.addClass(statusFun.cssAble);
}
function getBonusAjax(){
	// 查询积分制附加题学习情况
	url = '/jf/puresport/t7Crcl/query_bonus_status_05';
	dataJson = {
			type: 4
	};
	funSucc = function(data){
		jifenDataModel.bonus_status_list = data.bonus_status_list;
		list = jifenDataModel.bonus_status_list;
		for(var i=0; i< list.length; i++){
			// bonus 1~10 对应
			setBonusStatus(list[i].category, list[i].score);
		}
	}
	
	funFail = function(){
		
	}
	
	ajaxGet(url, dataJson, funSucc, funFail, testDataBonus);
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
