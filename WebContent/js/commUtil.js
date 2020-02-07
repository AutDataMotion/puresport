// -----string util

//var str = "js实现用{two}自符串替换占位符{two} {three}  {one} ".format({one: "I",two: "LOVE",three: "YOU"});
//var str2 = "js实现用{1}自符串替换占位符{1} {2}  {0} ".format("I","LOVE","YOU");
String.prototype.format = function() {
 if(arguments.length == 0) return this;
 var param = arguments[0];
 var s = this;
 if(typeof(param) == 'object') {
  for(var key in param)
   s = s.replace(new RegExp("\\{" + key + "\\}", "g"), param[key]);
  return s;
 } else {
  for(var i = 0; i < arguments.length; i++)
   s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
  return s;
 }
}
	
	
// -----validate
function validateComm(rule, v, lenMin, lenMax) {
	if (!v)
		return false;
	if (v.length < lenMin || v.length > lenMax) {
		return false;
	}
	if (!rule.test(v)) {
		return false;
	}
	return true;
}
function validatePhone(v) {
	var reg = /(^1\d{10}$)|(^[0-9]\d{7}$)/;
	return validateComm(reg, v, 11, 11);
};
function validateEmail(v) {
	var reg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	return validateComm(reg, v, 5, 50);
};
function validateID(v) {
	var idcardReg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	return validateComm(idcardReg, v, 18, 18);
};

// ------------set Obj
function btnSetDisable(domObj, disabled, text){
	if(disabled===true){
		domObj.prop('disabled', true);
		domObj.css({'background-color':'rgb(160, 162, 163)'});
		if(text){
			domObj.text(text);
		}
		
	}else {
		domObj.prop('disabled', false);
		domObj.css({'background-color':'rgb(30, 159, 255)'});
		if(text){
			domObj.text(text);
		}
	}
}
