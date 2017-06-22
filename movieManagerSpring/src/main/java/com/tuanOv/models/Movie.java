package com.tuanOv.models;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="movie")
public class Movie {
	@Id
	private String id;
	
	@NotBlank
	@Size(max=250)
	@Indexed(unique=true)
	private String title;
	
	private String description;
	
	private String language;
	
	private double price;
	
	private String imageFilename;
	
	@CreatedDate
	private Date createdAt;
	
	@LastModifiedDate
	private Date updatedAt;
	
	public Movie() {		
	}

	public String getId() {
		return id;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
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
	
	public void autoSetUpdatedAt() {
		this.updatedAt = new Date();
	}
	
	public void autoSetCreatedAt() {
		this.createdAt = new Date();
	}

	@Override
	public String toString() {
		return String.format(
				"Movie [id=%s, title=%s, description=%s, language=%s, price=%s, createdAt=%s, updatedAt=%s]", id, title,
				description, language, price, createdAt, updatedAt);
	}	
	
	
}
