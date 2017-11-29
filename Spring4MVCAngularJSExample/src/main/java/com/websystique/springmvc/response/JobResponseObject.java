package com.websystique.springmvc.response;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.vo.JobVO;

public class JobResponseObject extends GenericModelResponseObject<JobVO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -788355772286009639L;

	public JobResponseObject(GenericRequestObject request) {
		super(request);
	}
	
}
