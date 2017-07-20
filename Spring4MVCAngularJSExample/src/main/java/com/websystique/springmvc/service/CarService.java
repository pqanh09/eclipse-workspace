package com.websystique.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.repositories.CarDao;

@Service("carService")
public class CarService {
	@Autowired
    CarDao carDao;
 
    public void create() {
    	System.out.println("hi");
        carDao.create();
    }
}
