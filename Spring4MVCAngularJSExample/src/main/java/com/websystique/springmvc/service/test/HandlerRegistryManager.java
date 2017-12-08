package com.websystique.springmvc.service.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * registry for scheduler handler
 * 
 */
public final class HandlerRegistryManager implements RegistryManager<JobSchedulerHandler> {

    /**
     * handler scheduler map
     */
    private ConcurrentMap<String, JobSchedulerHandler> handlerMap = new ConcurrentHashMap<String, JobSchedulerHandler>();
    private static HandlerRegistryManager registryManager = null;
    
    /**
     * private constructor
     */
    private HandlerRegistryManager() {
    }

    /**
     * get manager instance
     * @return
     */
    public static HandlerRegistryManager getManager() {
        if (registryManager == null) {
            synchronized (HandlerRegistryManager.class) {
                if (registryManager == null) {
                    registryManager = new HandlerRegistryManager();
                }
                
            }
        }
        return registryManager;
    }
    
    @Override
    public void register(String handlerName, JobSchedulerHandler handlerScheduler) {
    	if(handlerMap.get(handlerName) == null){
    		handlerMap.put(handlerName, handlerScheduler);
    	}
    }

    @Override
    public void remove(String handlerName) {
        handlerMap.remove(handlerName);
    }
    
    @Override
    public JobSchedulerHandler retrieve(String handlerName) {
        return handlerMap.get(handlerName);
    }
    
    @Override
    public boolean isExist(String name) {
        return handlerMap.containsKey(name);
    }
}
