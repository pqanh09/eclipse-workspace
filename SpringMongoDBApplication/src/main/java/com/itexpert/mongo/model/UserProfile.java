package com.itexpert.mongo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserProfile")
public class UserProfile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2599727653624650224L;
	@Id
	private String id;
	@Indexed
	private String email;
	@Indexed
	private String password;
	private String status = "Unlock";
	private String firstName;
	private String middleName;
	private String lastName;
	private String phone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", email=" + email + ", password=" + password + ", status=" + status
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", phone="
				+ phone + "]";
	}
	
}
