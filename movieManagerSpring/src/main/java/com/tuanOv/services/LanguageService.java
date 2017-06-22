package com.tuanOv.services;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.tuanOv.models.Language;
import com.tuanOv.models.LanguageList;
import com.tuanOv.models.LanguageQuery;
import com.tuanOv.models.RestResponseGeneral;
import com.tuanOv.repositories.LanguageRepository;

@Service
public class LanguageService {
	private LanguageRepository languageRepository;	
	
	public LanguageService(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}
	
	public ResponseEntity<RestResponseGeneral> getAllLanguageList() {
		String message;
		try {
			List<Language> result = languageRepository.findAll();
			if (result.size() > 0) {
				return UltilityService.getSuccessResponseEntityByData(result);
			} else {
				message = "Data is empty!";
				return UltilityService.getErrorResponseEntityByMessage(message);
			}
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
	public ResponseEntity<RestResponseGeneral> getLanguageById(String id) {
		String message;
		try {
			Language language = languageRepository.findOne(id);
			if (language == null) {
				message = "No data found!";
				return UltilityService.getErrorResponseEntityByMessage(message);
			} else {
				return UltilityService.getSuccessResponseEntityByData(language);
			}
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
	public ResponseEntity<RestResponseGeneral> createLanguage(Language newLanguage) {
		String message;
		try {
			newLanguage.autoSetCreatedAt();
			newLanguage.autoSetUpdatedAt();
			languageRepository.save(newLanguage);
			message = "Created new language successfully!";
			return UltilityService.getSuccessResponseEntityByMessage(message);			
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
	public ResponseEntity<RestResponseGeneral> editLanguage(Language editedLanguage, @PathVariable("id") String id) {
		String message = "";
		
		try {
			Language languageToEdit = languageRepository.findOne(id);
			if (languageToEdit == null) {
				message = "Not found such language to edit!";
				return UltilityService.getErrorResponseEntityByMessage(message);
			} else {
				languageToEdit.setName(editedLanguage.getName());
				languageToEdit.setAlias(editedLanguage.getAlias());				
				languageToEdit.autoSetUpdatedAt();
				Language returnedLanguage = languageRepository.save(languageToEdit);
				message = "Edited language successfully!";
				return UltilityService.getSuccessResponseEntityByDataAndMessage(returnedLanguage, message);
			}
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
	public ResponseEntity<RestResponseGeneral> deleteLanguageById(String id) {
		String message;
		try {
			languageRepository.delete(id);
			message = "Delete selected item successfully!";
			return UltilityService.getSuccessResponseEntityByMessage(message);
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
	
	public ResponseEntity<RestResponseGeneral> deleteLanguageList(LanguageList languageList) {
		String message;
		try {
			List<String> idList = languageList.getLanguageList();
			languageRepository.deleteLanguageListByIdList(idList);
			message = "Delete selected item(s) successfully!";
			return UltilityService.getSuccessResponseEntityByMessage(message);
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
	public ResponseEntity<RestResponseGeneral> findLanguageListByNameOrAlias(LanguageQuery languageQuery) {
		String message;
		try {
			List<Language> languageList = languageRepository.findByNameLikeIgnoreCaseOrAliasLikeIgnoreCase(languageQuery.getName(), languageQuery.getAlias());
			if (languageList.size() == 0) {
				message = "No data found!";
				return UltilityService.getErrorResponseEntityByMessage(message);
			} else {
				return UltilityService.getSuccessResponseEntityByData(languageList);
			}
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
}
