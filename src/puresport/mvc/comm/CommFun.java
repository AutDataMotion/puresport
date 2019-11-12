package puresport.mvc.comm;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

import puresport.constant.EnumStatus;

public final class CommFun {
	
	public static JSONObject resJsonFail(String msg) {
		JSONObject json = new JSONObject();
		json.put("flag", false);
		json.put("msg", msg);
		return json;
	};
	
	public static JSONObject resJsonFail(EnumStatus status) {
		if (null == status) {
			return resJsonFail(StringUtils.EMPTY);
		}
		return resJsonFail(status.getName());
	};

}
