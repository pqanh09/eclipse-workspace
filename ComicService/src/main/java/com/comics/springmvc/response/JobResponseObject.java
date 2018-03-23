package com.comics.springmvc.response;

import com.comics.springmvc.request.GenericRequestObject;
import com.comics.springmvc.vo.JobVO;

public class JobResponseObject extends GenericModelResponseObject<JobVO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -788355772286009639L;

	public JobResponseObject(GenericRequestObject request) {
		super(request);
	}
	
}
