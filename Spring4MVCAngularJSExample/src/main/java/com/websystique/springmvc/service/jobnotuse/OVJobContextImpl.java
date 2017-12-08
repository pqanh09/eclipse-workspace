package com.websystique.springmvc.service.jobnotuse;

import org.quartz.JobDataMap;

public class OVJobContextImpl implements OVJobContext {

	JobDataMap jobDataMap;

	public OVJobContextImpl(JobDataMap jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

	public void putAsString(String key, boolean value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, Boolean value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, char value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, Character value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, double value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, Double value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, float value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, Float value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, int value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, Integer value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, long value) {
		jobDataMap.putAsString(key, value);

	}

	public void putAsString(String key, Long value) {
		jobDataMap.putAsString(key, value);

	}

	public int getIntFromString(String key) {

		return jobDataMap.getIntFromString(key);
	}

	public int getIntValue(String key) {

		return jobDataMap.getIntValue(key);
	}

	public Integer getIntegerFromString(String key) {

		return jobDataMap.getIntegerFromString(key);
	}

	public boolean getBooleanValueFromString(String key) {

		return jobDataMap.getBooleanValueFromString(key);
	}

	public boolean getBooleanValue(String key) {

		return jobDataMap.getBooleanValue(key);
	}

	public Boolean getBooleanFromString(String key) {

		return jobDataMap.getBooleanFromString(key);
	}

	public char getCharFromString(String key) {

		return jobDataMap.getCharFromString(key);
	}

	public Character getCharacterFromString(String key) {

		return jobDataMap.getCharacterFromString(key);
	}

	public double getDoubleValueFromString(String key) {

		return jobDataMap.getDoubleValueFromString(key);
	}

	public double getDoubleValue(String key) {

		return jobDataMap.getDoubleValue(key);
	}

	public Double getDoubleFromString(String key) {

		return jobDataMap.getDoubleFromString(key);
	}

	public float getFloatValueFromString(String key) {

		return jobDataMap.getFloatValueFromString(key);
	}

	public float getFloatValue(String key) {

		return jobDataMap.getFloatValue(key);
	}

	public Float getFloatFromString(String key) {

		return jobDataMap.getFloatFromString(key);
	}

	public long getLongValueFromString(String key) {

		return jobDataMap.getLongValueFromString(key);
	}

	public long getLongValue(String key) {

		return jobDataMap.getLongValue(key);
	}

	public Long getLongFromString(String key) {

		return jobDataMap.getLongFromString(key);
	}

	public void put(String key, Object value) {
		jobDataMap.put(key, value);
	}

	public Object get(String key) {
		return jobDataMap.get(key);
	}
}
