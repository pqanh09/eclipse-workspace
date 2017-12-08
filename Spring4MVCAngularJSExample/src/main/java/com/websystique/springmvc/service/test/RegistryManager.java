package com.websystique.springmvc.service.test;


public interface RegistryManager<T> {
   
    public void register(String name, T entry);
    
    public void remove(String name);
   
    public T retrieve(String name);
    
    public boolean isExist(String name);
}
