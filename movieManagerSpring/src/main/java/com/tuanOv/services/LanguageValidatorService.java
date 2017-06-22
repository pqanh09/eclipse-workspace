package com.tuanOv.services;


import java.util.ArrayList;
import java.util.List;

import com.tuanOv.models.Language;
import com.tuanOv.models.ValidatedResult;

public class LanguageValidatorService {	
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
	
	public static ValidatedResult validate(Language language) {
		boolean result = true;
		List<String> messageList = new ArrayList<String>();
		String allMessage = "";
		if (language.getName().isEmpty()) {
			messageList.add(getErrorMessage_NotExist("Language Name"));	
			result = false;
		}
		if (language.getAlias().isEmpty()) {			
			messageList.add(getErrorMessage_NotExist("Alias"));
			result = false;
		}
		
		if (messageList.size() > 0) {
			allMessage = LanguageValidatorService.convertStringListToString(messageList);
		}
		return new ValidatedResult(result, allMessage);
	}	
	
	public static boolean isValid(Language language) {
		ValidatedResult ret = LanguageValidatorService.validate(language);
		return ret.getResult();
	}
}
