package com.taurus.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	@Autowired
	List<Integer> listBean;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(){
		System.out.println("index");
		if(listBean != null){
			System.out.println("ListBean:" + listBean.toString());
		}
		
		return "source/index.html";
	} 
}
