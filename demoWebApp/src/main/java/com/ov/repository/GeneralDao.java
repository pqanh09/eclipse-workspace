package com.ov.repository;

import java.util.List;


public interface GeneralDao<E>{
//	public void addCollection(T t, Class<T> classType);
	public E addCollection(E e);
	public E getCollection(String id);
	public List<E> getCollections();
	public E updateCollection(E e);
	public E deleteCollection(String id);
	public boolean checkExist(String id);
}
