

package com.websystique.springmvc.vo;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.MapType;
import com.websystique.springmvc.model.GenericModel;


public abstract class AbstractDelegatorVO<T extends GenericModel<ID>, ID> extends GenericVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 14716724781522026L;
	protected T delegate;
	
	public AbstractDelegatorVO() {
		this(null);
	}
	/**
	 * 
	 */
	public AbstractDelegatorVO(T object) {
		if (object == null){
			delegate = newInstance();
		} else {
			this.delegator(object);
		}
	}
	
	public T delegator(){
		return this.delegate;
	}
	public int hashCode() {
		return delegate.hashCode();
	}
	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}
	protected void delegator(T delegate){
		// support test
		this.delegate = delegate;
		init();
	}
	/**
	 * @return
	 * @see com.alu.ov.ngnms.domain.model.GenericModel#getInstanceid()
	 */
	public String getInstanceid() {
		return String.valueOf(delegate.getInstanceid());
	}
	/**
	 * @param instanceid
	 * @see com.alu.ov.ngnms.domain.model.GenericModel#setInstanceid(java.lang.Object)
	 */
	public void setInstanceid(String instanceid) {
		delegate.setInstanceid(covertInstanceId(instanceid));
	}
	/**
	 * @param instanceid
	 * @return
	 */
	protected abstract ID covertInstanceId(String instanceid);
	
	/**
	 * 
	 */
	protected void init() {
	}
	/**
	 * @return
	 */
	protected abstract T newInstance();
	public void mapData(Map<String, Object> data) throws IllegalAccessException, InvocationTargetException{
		Map<String, Object> tempData = mapData();
		tempData.putAll(data);
		T tmpDelegate = (T) mapper.convertValue(tempData, this.delegate.getClass());
		this.delegate = tmpDelegate;
	}

	public Map<String,Object> mapData(){
		@SuppressWarnings("unchecked")
		MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
		Map<String,Object> result = mapper.convertValue(delegate, mapType);
		result.remove("createDate");
		result.remove("modifiedDate");
		result.remove("lastUpdatedBy");
		result.remove("version");
		return result;
	}
	public static Map<String, Object> removeNullValues(Map<String, Object> data){
		Map<String, Object> result = new HashMap<String, Object>();
		for(String key: data.keySet()){
			if (data.get(key)!=null){
				result.put(key, data.get(key));
			}
		}
		return result;
	}
	private static final ObjectMapper mapper;
	static {
		
		mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("ObjectIdmodule", Version.unknownVersion());
		module.addSerializer(ObjectId.class, new ObjectIdSerializer());
		mapper.registerModule(module);
	}
	private static class ObjectIdSerializer extends JsonSerializer<ObjectId> {
        @Override
        public void serialize(ObjectId value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeString(value.toString());
        }
    }
}
