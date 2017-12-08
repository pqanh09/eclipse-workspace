package com.websystique.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BlockingQueue drop = new LinkedBlockingQueue(10);
		List<String> a1 = new ArrayList<>();
		a1.add("1");
		a1.add("2");
		List<String> a2 = new ArrayList<>();
		a2.add("2");
		a2.add("1");
		
		System.out.println(a2.containsAll(a1));
//		
//		List jobHistory = new ArrayList<>();
//		
//		
////		BlockingQueue jobHistory = new LinkedBlockingQueue(12);
//		int i = 0;
//		while(i<15){
//			if(jobHistory.size() == 12){
//				System.out.println("Remove" + jobHistory.remove(0).toString());
//			}
//			jobHistory.add(i);
//			i ++;
//			System.out.println(jobHistory);
//		}
		
		
	}

}
