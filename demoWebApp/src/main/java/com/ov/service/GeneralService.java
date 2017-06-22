package com.ov.service;


import com.ov.model.GeneralModel;
import com.ov.utils.ResponseGeneral;

public interface GeneralService<E extends GeneralModel> {
	ResponseGeneral handleGetCollections();
	ResponseGeneral handleGetCollectionById(String id);
	ResponseGeneral handleBadRequest(String string);
	ResponseGeneral handleAddCollection(E model);
	ResponseGeneral handleUpdateCollection(E model);
	ResponseGeneral handleDeleteCollection(String id);
}
