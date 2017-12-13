package com.websystique.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main3 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		BlockingQueue drop = new LinkedBlockingQueue(10);
		List<String> a1 = new ArrayList<>();
		a1.add("1");
		a1.add("2");
		List<String> a2 = new ArrayList<>();
		a2.add("2");
		a2.add("1");
		
//		System.out.println(a2.containsAll(a1));
//		
//		double d = Double.parseDouble("0.50173311");
//		System.out.println(d);
//		System.out.println(Double.MAX_VALUE);
//		System.out.println(Double.MIN_VALUE);
		
		
		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTimeInMillis());
		Date date = new Date();
		System.out.println(date.getTime());
		System.out.println(date);
		date.setSeconds(0);
//		date.set
		System.out.println(date.getTime());
		System.out.println(date);
		System.out.println(date.getMinutes());
//		Thread.sleep(2000);
		
//		System.out.println(cal.getTimeInMillis());
//		System.out.println(date.getTime());

//		Date date = new Date();
		Date d = new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), 0);
		System.out.println(d.getTime());
		System.out.println(d);
		
		
		
		
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
