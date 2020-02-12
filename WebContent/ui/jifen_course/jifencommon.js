// depend on  layui.js

jifenTest = false;

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

function tipsAlert(msg, title, btnText, btnFunCallBack, height) {
	$('#tip_content').html(msg);
	var _title = '提示';
	var _btnText = '确定';
	
	if(title){
		_title = title;
	}
	if(btnText){
		_btnText = btnText;
	}
	
	if(height){
		$('#tip_content').css('height', height);
	} else {
		$('#tip_content').css('height', '');
	}
	
	var tipModal = $('#tipModal');
	var tipBtn = $('#tip_btn_ok');
	if(btnFunCallBack  && typeof btnFunCallBack === "function"){
		tipBtn.click(function(){
			// $('#tipModal').modal('hide');
			btnFunCallBack();
		});
	} else {
		tipBtn.click(function(){
			tipModal.modal('hide');
		});
	}
	$('#tips_title').text(_title);
	$('#tip_btn_ok').text(_btnText);
	tipModal.modal('show');
	
}

function ajaxCommonFunSuc(data, funSucc, funFail, url){
	if(data==undefined || data.code==undefined || data.code != '0000'){
		// fail
//		layer.confirm('抱歉，系统开小差了，请稍后重试！', {
//			icon : 3,
//			title : '系统提示'
//		}, function(index) {
//			typeof funFail === "function" ? funFail() : false;
//			layer.close(index);
//		});
		// tipsAlert('抱歉，系统开小差了，请稍后重试！');
		typeof funFail === "function" ? funFail() : false;
		console.log(url, data.desc);
	}else{
		// success
		typeof funSucc === "function" ? funSucc(data) : false;
	}
}

function ajaxCommon(getOrPost, url, dataJson, funSucc, funFail, rspTestData){
	
	if(jifenTest){
		ajaxCommonFunSuc(rspTestData, funSucc, funFail, url);
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
			ajaxCommonFunSuc(data, funSucc, funFail, url);
		},
		error : function(xhr, textStatus) {
			tipsAlert('抱歉，系统开小差了，请稍后重试！');
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
		if(globalIsMobile=='1'){
			$("#score").css("height",jifenDataModel.exam_grd+"%");
		} else {
			$("#score").css("width",jifenDataModel.exam_grd+"%");
		}
		
	}
	
	funFail = function(){
		
	}
	
	ajaxGet(url, dataJson, funSucc, funFail, testDataScore);
}


function getCourseUrl(id){
	return cxt + '/jf/puresport/t7Crcl/get_study_notify_tokyo?which_competition=4&category=' + id;
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

function getYunHtmlUrl(id){
	var htmlUrl = cxt + '/ui/jifen_course/yundoc/doc'+id+'.html';
	return '<div class="modal-body embed-responsive embed-responsive-4by3" style="height:500px;">' 
			+ '<iframe class="embed-responsive-item" src="'+htmlUrl +'"></iframe>'
			+ '</div>';
}

function ajaxBonusClick(id){
	// 查询积分制附加题学习情况
	url = '/jf/puresport/t7Crcl/get_bonus';
	dataJson = {
			category: id
	};
	funSucc = function(data){
		// 成功后不可再点击
		setBonusStatus(id, 1);
		initAll();
	}
	
	funFail = function(){
		initAll();
	}
	
	ajaxGet(url, dataJson, funSucc, funFail);
}

var bonusStatusMap = {
		'-1':{
			name:'未上线', 
			cssAble:'disable',
			target:'',
			funClick:function(id){
				//layer.msg('此项目未上线');
				tipsAlert('此项目未上线');
			}
		},
		'0':{
			name:'未学习', 
			cssAble:'',
			target:'',
			funClick:function(id){
				tipsAlert(getYunHtmlUrl(id), '附加题', '确定', function(){
					ajaxBonusClick(id);
				},'500px');
			}
		},
		'1':{
			name:'已学习',
			cssAble:'',
			target:'',
			funClick:function(id){
				tipsAlert('此项目您已学习');
			}
		},
};
// 上线bonus 从1 开始 到 10
var bonusOnline = [1];

function setBonusStatus(id, score){
	console.log('setBonusStatus', id, score);
	var imgDiv = $('#yun'+id);
	var statusFun = bonusStatusMap[score];
	for(var i= 0; i< bonusOnline.length; i++){
		if(bonusOnline[i]==id){
			// 上线了
			imgDiv.click(function(){
				statusFun.funClick(id)
				});
			imgDiv.attr('target',statusFun.target);
			imgDiv.addClass(statusFun.cssAble);
			return ;
		}
	}
	// 未上线
	statusFun = bonusStatusMap['-1'];
	imgDiv.click(function(){
		statusFun.funClick(id)
	});
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
			// bonus 1~10 对应  scor 这里少个e的
			setBonusStatus(list[i].category, list[i].scor);
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
