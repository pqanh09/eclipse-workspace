package com.comics.springmvc.service;

import java.util.List;

import com.comics.springmvc.model.User;



public interface UserService {
	
	
	User findByUsername(String username);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
	
	void initUsers();
}
