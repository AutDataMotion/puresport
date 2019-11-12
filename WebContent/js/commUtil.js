
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
