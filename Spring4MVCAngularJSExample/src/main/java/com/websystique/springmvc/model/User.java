package com.websystique.springmvc.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User extends GenericModel<ObjectId>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1486157648065779580L;
	
	public static final String COLLECTION_NAME = "User";
	
	@NotNull
	@Size(max=250)	
	@Indexed(unique = true)
	private String username;
	
	private String address;
	
	@NotNull
	@Size(max=250)	
	private String email;
	
	public User(){
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", address=" + address + ", email=" + email + "]";
	}

	public User(ObjectId instanceid, Date createDate, Date modifiedDate, String lastUpdatedBy) {
		super(instanceid, createDate, modifiedDate, lastUpdatedBy);
		// TODO Auto-generated constructor stub
	}

	public User(String username, String address, String email) {
		this.username = username;
		this.address = address;
		this.email = email;
	}
	

	
}
