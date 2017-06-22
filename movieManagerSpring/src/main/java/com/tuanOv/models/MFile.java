package com.tuanOv.models;

import org.apache.commons.io.FilenameUtils;

public class MFile {
	private String filename;
	private String extension;
	
	public MFile(String filename, String extension) {		
		this.filename = filename;
		this.extension = extension;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public static MFile getMFileByFullFilename(String fullFilename) {
		String fileName = FilenameUtils.removeExtension(fullFilename);
		String extension = FilenameUtils.getExtension(fullFilename);
		MFile result = new MFile(fileName, extension);
		return result;
	}
	

}
