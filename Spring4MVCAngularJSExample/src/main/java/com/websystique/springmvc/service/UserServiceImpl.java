package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.repositories.UserRepository;

//@Service("userService")
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserRepository userRepository;
	
	//public UserServiceImpl(){
		//userRepository.safeSave(populateDummyUsers());
	//}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public User findByUsername(String name) {
		return userRepository.findByUsername(name);
	}
	
	
	public void saveUser(User user) {
		userRepository.safeSave(user);
	}

	public void updateUser(User user) {
		userRepository.safeSave(user);
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public boolean isUserExist(User user) {
		return findByUsername(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		userRepository.deleteAll();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User("Sam", "NY", "sam@abc.com"));
		users.add(new User("Tomy", "ALBAMA", "tomy@abc.com"));
		users.add(new User("Kelly", "NEBRASKA", "kelly@abc.com"));
		return users;
	}


}
