package com.tuanOv.models;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

public class Language {
	@Id
	private String id;
	
	@NotBlank
	@Size(max=250)	
	private String name;
	
	@NotBlank
	private String alias;
		
	@CreatedDate
	private Date createdAt;
	
	@LastModifiedDate
	private Date updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void autoSetUpdatedAt() {
		this.updatedAt = new Date();
	}
	
	public void autoSetCreatedAt() {
		this.createdAt = new Date();
	}
}
