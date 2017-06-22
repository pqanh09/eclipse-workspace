package com.tuanOv.services;


import java.util.ArrayList;
import java.util.List;

import com.tuanOv.models.Movie;
import com.tuanOv.models.ValidatedResult;

public class MovieValidatorService {	
	private static String getErrorMessage_NotExist(String target) {
		return String.format(
				ValidatedResult.MessageConst.ERR_NOT_EXISTS.value(),
				target);
	}
	
	private static String convertStringListToString(List<String> stringList) {
		StringBuilder sb = new StringBuilder();
		for (String s : stringList) {
			sb.append(s);
			sb.append(", ");
		}
		
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}
	
	private static List<String> validateGeneral(Movie movie, List<String> messageList) {
		List<String> resultMessageList = messageList;
		if (movie.getTitle().isEmpty()) {
			resultMessageList.add(getErrorMessage_NotExist("Title"));
		}
		
		if (movie.getDescription().isEmpty()) {			
			resultMessageList.add(getErrorMessage_NotExist("Description"));			
		}
		
		return resultMessageList;
	}
	
	public static ValidatedResult validateAdd(Movie movie) {
		boolean result = true;
		List<String> messageList = new ArrayList<String>();
		String allMessage = "";
		
		messageList = validateGeneral(movie, messageList);
		
		if (movie.getImageFilename().isEmpty()) {
			String message = "Movie Image must be uploaded!";
			messageList.add(message);			
		}
		
		if (messageList.size() > 0) {
			result = false;
			allMessage = MovieValidatorService.convertStringListToString(messageList);
		}
		return new ValidatedResult(result, allMessage);
	}
	
	public static ValidatedResult validateEdit(Movie movie) {
		boolean result = true;
		List<String> messageList = new ArrayList<String>();
		String allMessage = "";
		
		messageList = validateGeneral(movie, messageList);
		
		if (messageList.size() > 0) {
			result = false;
			allMessage = MovieValidatorService.convertStringListToString(messageList);
		}
		return new ValidatedResult(result, allMessage);
	}
	
	public static boolean isValid(Movie movie) {
		ValidatedResult ret = MovieValidatorService.validateAdd(movie);
		return ret.getResult();
	}
}
