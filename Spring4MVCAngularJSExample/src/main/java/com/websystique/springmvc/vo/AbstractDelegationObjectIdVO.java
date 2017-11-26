

package com.websystique.springmvc.vo;

import java.util.Map;

import org.bson.types.ObjectId;

import com.websystique.springmvc.model.GenericModel;


public abstract class AbstractDelegationObjectIdVO<T extends GenericModel<ObjectId>> extends AbstractDelegatorVO<T, ObjectId> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AbstractDelegationObjectIdVO() {
		super();
	}
	/**
	 * @param object
	 */
	public AbstractDelegationObjectIdVO(T object) {
		super(object);
	}

	/* (non-Javadoc)
	 * @see com.alu.ov.ngnms.ag2.vo.AbstractDelegatorVO#covertInstanceId(java.lang.String)
	 */
	@Override
	protected ObjectId covertInstanceId(String instanceid) {
		return new ObjectId(instanceid);
	}
	public String retrieveNGObjectType(String osVersion) {
		return null;
	}
	

	public Map<String, Object> attributesData(String osVersion) {
		Map<String, Object> result = mapData();
		result.remove(GenericModel.ATTR_INSTANCE_ID_NAME);
		return result;
	}
}
