package com.tuanOv.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanOv.models.Language;
import com.tuanOv.models.LanguageList;
import com.tuanOv.models.LanguageQuery;
import com.tuanOv.models.RestResponseGeneral;
import com.tuanOv.models.ValidatedResult;
import com.tuanOv.services.LanguageService;
import com.tuanOv.services.LanguageValidatorService;
import com.tuanOv.services.UltilityService;

@Controller
@RequestMapping("/api/languages")
public class LanguageController {
	
	private LanguageService languageService;
	
	public LanguageController(LanguageService languageService) {
		this.languageService = languageService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<RestResponseGeneral> getAllLanguageList() {
		return this.languageService.getAllLanguageList();
	}

	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<RestResponseGeneral> getLanguageById(@PathVariable("id") String id) {
		return this.languageService.getLanguageById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<RestResponseGeneral> createLanguage(@RequestBody Language newLanguage) {
		ValidatedResult validRes = LanguageValidatorService.validate(newLanguage);
		
		if (validRes.getResult()) {
			return this.languageService.createLanguage(newLanguage);
		}
		
		return UltilityService.getErrorResponseEntityByMessage(validRes.getMessage());		
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<RestResponseGeneral> editLanguage(@RequestBody Language editedLanguage, @PathVariable("id") String id) {
ValidatedResult validRes = LanguageValidatorService.validate(editedLanguage);
		
		if (validRes.getResult()) {
			return this.languageService.editLanguage(editedLanguage, id);
		}
		
		return UltilityService.getErrorResponseEntityByMessage(validRes.getMessage());
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<RestResponseGeneral> deleteLanguageById(@PathVariable("id") String id) {
		return this.languageService.deleteLanguageById(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<RestResponseGeneral> deleteLanguageList(@RequestBody LanguageList deletedLanguageList) {
		return this.languageService.deleteLanguageList(deletedLanguageList);	
	}
	
	@RequestMapping(value="/find", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<RestResponseGeneral> findLanguageListByNameOrAlias(@RequestBody LanguageQuery languageQuery) {
		return this.languageService.findLanguageListByNameOrAlias(languageQuery);	
	}
	
}
