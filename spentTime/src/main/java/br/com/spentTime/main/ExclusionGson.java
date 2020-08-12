package br.com.spentTime.main;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ExclusionGson implements ExclusionStrategy {
	
	private Class<?> className;
	private String fieldName;
	
	public ExclusionGson(Class<?> class1, String fieldName) {
		this.className = class1;
		this.fieldName = fieldName;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return (f.getDeclaringClass() == this.className && f.getName().equals(this.fieldName));
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}
