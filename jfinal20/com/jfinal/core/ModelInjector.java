/**
 * Copyright (c) 2011-2015, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jfinal.core;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;

import csuduc.platform.util.tuple.Tuple2;
import csuduc.platform.util.tuple.TupleUtil;

/**
 * ModelInjector
 */
public final class ModelInjector {
	
	@SuppressWarnings("unchecked")
	public static <T> T inject(Class<?> modelClass, HttpServletRequest request, boolean skipConvertError) {
		String modelName = modelClass.getSimpleName();
		return (T)inject(modelClass, StrKit.firstCharToLowerCase(modelName), request, skipConvertError);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final <T> T inject(Class<?> modelClass, String modelName, HttpServletRequest request, boolean skipConvertError) {
		Object model = null;
		try {
			model = modelClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		if (model instanceof Model)
			injectActiveRecordModel((Model)model, modelName, request, skipConvertError);
		else
			injectCommonModel(model, modelName, request, modelClass, skipConvertError);
		
		return (T)model;
	}
	
	private static final void injectCommonModel(Object model, String modelName, HttpServletRequest request, Class<?> modelClass, boolean skipConvertError) {
		Method[] methods = modelClass.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith("set") == false)	// only setter method
				continue;
			
			Class<?>[] types = method.getParameterTypes();
			if (types.length != 1)						// only one parameter
				continue;
			
			String attrName = methodName.substring(3);
			String value = request.getParameter(modelName + "." + StrKit.firstCharToLowerCase(attrName));
			if (value != null) {
				try {
					method.invoke(model, TypeConverter.convert(types[0], value));
				} catch (Exception e) {
					if (skipConvertError == false)
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static final void injectActiveRecordModel(Model<?> model, String modelName, HttpServletRequest request, boolean skipConvertError) {
		Table table = TableMapping.me().getTable(model.getClass());
		
		String modelNameAndDot = modelName + ".";
		
		Map<String, String[]> parasMap = request.getParameterMap();
		for (Entry<String, String[]> e : parasMap.entrySet()) {
			String paraKey = e.getKey();
			if (paraKey.startsWith(modelNameAndDot)) {
				String paraName = paraKey.substring(modelNameAndDot.length());
				Class colType = table.getColumnType(paraName);
				if (colType == null)
					throw new ActiveRecordException("The model attribute " + paraKey + " is not exists.");
				String[] paraValue = e.getValue();
				try {
					// Object value = Converter.convert(colType, paraValue != null ? paraValue[0] : null);
					Object value = paraValue[0] != null ? TypeConverter.convert(colType, paraValue[0]) : null;
					model.set(paraName, value);
				} catch (Exception ex) {
					if (skipConvertError == false)
						throw new RuntimeException("Can not convert parameter: " + modelNameAndDot + paraName, ex);
				}
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final <T> T injectForTable(Class<?> modelClass, String modelName, HttpServletRequest request) {
		Object model = null;
		try {
			model = modelClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// data[0][nm]:添加运动1
		// data[0][crdt_tp]:身份证
		if (model instanceof Model)
			injectRecordModelForTable((Model)model, modelName, request,false
					, (tuple2) ->  tuple2.first.startsWith(tuple2.second)
					, (nameKey)-> nameKey.substring(modelName.length()+1, nameKey.length()-1)
					);
		else
			injectCommonModel(model, modelName, request, modelClass, false);
		
		return (T)model;
	}
	@SuppressWarnings({ "rawtypes", "unused" })
	private static final void injectRecordModelForTable(Model<?> model
			, String modelName
			, HttpServletRequest request
			, boolean skipConvertError
			, Function<Tuple2<String, String>, Boolean> isSameParam
			, Function<String, String> getParamName) {
		
		Table table = TableMapping.me().getTable(model.getClass());
		Map<String, String[]> parasMap = request.getParameterMap();
		for (Entry<String, String[]> e : parasMap.entrySet()) {
			String paraKey = e.getKey();
			if (isSameParam.apply(TupleUtil.tuple(paraKey, modelName))) {
				String paraName = getParamName.apply(paraKey);
				Class colType = table.getColumnType(paraName);
				if (colType == null)
					throw new ActiveRecordException("The model attribute " + paraKey + " is not exists.");
				String[] paraValue = e.getValue();
				try {
					// Object value = Converter.convert(colType, paraValue != null ? paraValue[0] : null);
					Object value = paraValue[0] != null ? TypeConverter.convert(colType, paraValue[0]) : null;
					model.set(paraName, value);
				} catch (Exception ex) {
					if (skipConvertError == false)
						throw new RuntimeException("Can not convert parameter: " + modelName + paraName, ex);
				}
			}
		}
	}
}

