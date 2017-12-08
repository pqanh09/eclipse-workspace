package com.websystique.springmvc.service.test;

import java.security.SecureRandom;
import java.util.Random;

/*  
 * Generate tenant id depend on current time of system
 * make sure it unique by synchronized
 * */
public class TenantIdGenerator {
    
	//generate TenantID with LOWERCASE letters - fixed OVCLOUD-2604 TenantIDs with UPPERCASE letters cannot be used in domain names 
    final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    final static Random rng = new SecureRandom(); 
    
    public static synchronized String generateTenantID(int length) throws InterruptedException{
        long time = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (String subString : String.valueOf(time).split("(?<=\\G.{2})")) {
            int num = Integer.parseInt(subString)%ALPHABET.length();
            sb.append(ALPHABET.charAt(num));
        }
        while(sb.length() < length){
            sb.append(randomChar());
        }
        Thread.sleep(1);
        return sb.toString();
    }
    
    public static char randomChar(){
        return ALPHABET.charAt(rng.nextInt(ALPHABET.length()));
    }
    
}

