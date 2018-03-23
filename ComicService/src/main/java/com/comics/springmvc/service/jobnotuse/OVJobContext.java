package com.comics.springmvc.service.jobnotuse;


public interface OVJobContext {
	
	public void put(String key, Object value);
	
	public Object get(String key);
	
	public void putAsString(String key, boolean value);

	public void putAsString(String key, Boolean value);

	public void putAsString(String key, char value);

	public void putAsString(String key, Character value);

	public void putAsString(String key, double value);

	public void putAsString(String key, Double value);

	public void putAsString(String key, float value);

	public void putAsString(String key, Float value);

	public void putAsString(String key, int value);

	public void putAsString(String key, Integer value);

	public void putAsString(String key, long value);

	public void putAsString(String key, Long value);

	public int getIntFromString(String key);

	public int getIntValue(String key);

	public Integer getIntegerFromString(String key);

	public boolean getBooleanValueFromString(String key);

	public boolean getBooleanValue(String key);

	public Boolean getBooleanFromString(String key);

	public char getCharFromString(String key);

	public Character getCharacterFromString(String key);

	public double getDoubleValueFromString(String key);

	public double getDoubleValue(String key);

	public Double getDoubleFromString(String key);

	public float getFloatValueFromString(String key);

	public float getFloatValue(String key);

	public Float getFloatFromString(String key);

	public long getLongValueFromString(String key);

	public long getLongValue(String key);

	public Long getLongFromString(String key);
	
}
