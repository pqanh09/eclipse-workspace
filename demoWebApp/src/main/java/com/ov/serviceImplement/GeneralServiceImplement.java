package com.ov.serviceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.ov.model.GeneralModel;
import com.ov.repository.GeneralRepository;
import com.ov.responseEntity.GeneralObject;
import com.ov.service.GeneralService;
import com.ov.utils.ResponseGeneral;
import com.ov.utils.ResponseSongManager;
import com.ov.utils.ResponseStatus;

public abstract class GeneralServiceImplement<E extends GeneralModel> implements GeneralService<E>{

	protected GeneralRepository<E> generalRepository;
	protected ResponseGeneral response;
	public GeneralServiceImplement(){
	}
	
	public GeneralServiceImplement(GeneralRepository<E> generalRepository){
		this.generalRepository = generalRepository;
	}
	public GeneralObject convertObject(E e){
		return null;
	}

	public ResponseGeneral handleGetCollectionById(String id) {
		response = ResponseSongManager.responseBuilder();
		response.setVersion("v1");
		try {
//			E collection = collectionDao.getCollection(id);
			E collection = generalRepository.findOne(id);
			GeneralObject obj = convertObject(collection);
			response.setResponse(obj);
			response.setStatus(ResponseStatus.SUCCESS.toString());
			response.setStatusCode(HttpStatus.OK.value());
			response.setTypeResponse(obj.getClass().getSimpleName());
		} catch (Exception e) {
			System.out.println(e.toString());
			response.setStatus(ResponseStatus.FAIL.toString());
			response.setResponse("Error connect DB !");
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setTypeResponse(String.class.getSimpleName());
		}
		return response;
	}

	public ResponseGeneral handleGetCollections(){
		response = ResponseSongManager.responseBuilder();
		response.setVersion("v1");
		try {
//			List<E> collections = collectionDao.getCollections();
			List<E> collections = generalRepository.findAll();
			List<GeneralObject> objs = new ArrayList<GeneralObject>();
			for (E collection : collections) {
				objs.add(convertObject(collection));
			}
			response.setResponse(objs);
			response.setTypeResponse(objs.getClass().getSimpleName());
			response.setStatus(ResponseStatus.SUCCESS.toString());
			response.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			System.out.println(e.toString());
			response.setStatus(ResponseStatus.FAIL.toString());
			response.setResponse("Error connect DB !");
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setTypeResponse(String.class.getSimpleName());
		}
		
		return response;
	}
	
	public ResponseGeneral handleAddCollection(E model) {
		response = ResponseSongManager.responseBuilder();
		response.setVersion("v1");
		try {
//			E collection = collectionDao.addCollection(model);
			E collection = generalRepository.save(model);
			GeneralObject obj = convertObject(collection);
			response.setResponse(obj);
			response.setTypeResponse(obj.getClass().getSimpleName());
			response.setStatus(ResponseStatus.SUCCESS.toString());
			response.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			System.out.println(e.toString());
			response.setStatus(ResponseStatus.FAIL.toString());
			response.setResponse("Error connect DB !");
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setTypeResponse(String.class.getSimpleName());
		}
		return response;
	}

	public ResponseGeneral handleUpdateCollection(E model) {
		response = ResponseSongManager.responseBuilder();
		response.setVersion("v1");
		try {
//			E collection = collectionDao.updateCollection(model);
			E collection = generalRepository.save(model);
			GeneralObject obj = convertObject(collection);
			response.setResponse(obj);
			response.setTypeResponse(obj.getClass().getSimpleName());
			response.setStatus(ResponseStatus.SUCCESS.toString());
			response.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			System.out.println(e.toString());
			response.setStatus(ResponseStatus.FAIL.toString());
			response.setResponse("Error connect DB !");
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setTypeResponse(String.class.getSimpleName());
		}
		return response;
	}

	public ResponseGeneral handleDeleteCollection(String id) {
		response = ResponseSongManager.responseBuilder();
		response.setVersion("v1");
		try {
//			E collection = collectionDao.deleteCollection(id);
			E collection = generalRepository.findOne(id);
			generalRepository.delete(id);
			GeneralObject obj = convertObject(collection);
			response.setResponse(obj);
			response.setTypeResponse(obj.getClass().getSimpleName());
			response.setStatus(ResponseStatus.SUCCESS.toString());
			response.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			System.out.println(e.toString());
			response.setStatus(ResponseStatus.FAIL.toString());
			response.setResponse("Error connect DB !");
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setTypeResponse(String.class.getSimpleName());
		}
		return response;
	}

	public ResponseGeneral handleBadRequest(String string){
		response = ResponseSongManager.responseBuilder();
		response.setVersion("v1");
		response.setResponse(string);
		response.setTypeResponse(string.getClass().getSimpleName());
		response.setStatus(ResponseStatus.FAIL.toString());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		return response;
	}
	

}
