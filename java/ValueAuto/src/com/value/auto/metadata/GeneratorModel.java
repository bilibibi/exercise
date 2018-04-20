package com.value.auto.metadata;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class GeneratorModel {
	private Object model;
	
	public GeneratorModel(Object model) {
		this.model = model;
	}

	public String getDisaplyText() {
		return model.toString();
	}

	public void mergeFilePathModel(Map map) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	    map.putAll(BeanUtils.describe(model));
	}

	public void mergeTemplateModel(Map map) {
	    map.put("model", model);
	}

	public Object getModel() {
		return model;
	}

}
