package com.itexpert.dao;

import java.util.List;

import com.itexpert.mongo.model.UserProfile;

public interface BaseUserDAO<T extends UserProfile> {
	public void create(T user);
	public void remove(T id);
	public void removeByEmail(String id);
	public void removeAll();
	public T find(String id);
	public List findAll();
	public void update(T user);
	public UserProfile getUserProfileByEmail(String email);
}
