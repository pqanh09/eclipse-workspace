package com.comics.springmvc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.comics.springmvc.model.Chapter;
import com.comics.springmvc.model.ModelUtilProvider;
import com.comics.springmvc.model.Web;
import com.comics.springmvc.repositories.ChapterRepository;
import com.comics.springmvc.repositories.WebRepository;
import com.comics.springmvc.request.ChapterRequestObject;
import com.comics.springmvc.request.GenericRequestObject;
import com.comics.springmvc.response.ChapterResponseObject;
import com.comics.springmvc.response.GenericResponseObject;
import com.comics.springmvc.response.Messages;
import com.comics.springmvc.vo.ChapterVO;
import com.comics.springmvc.vo.WebVO;

@Service("chapterService")
public class ChapterServiceImpl extends AbstractServiceImpl implements ChapterService{
	
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(ChapterServiceImpl.class);
	@Autowired
	private ChapterRepository chapterRepository;
	
	//List attributes to include the Object when querying
	private static List<String> attrNames = Arrays.asList(
			Chapter.ATTR_NAME, 
			Chapter.ATTR_FULLNAME, 
			Chapter.ATTR_MANGA_ID, 
			Chapter.ATTR_ORDINAL_NUMBER, 
			Chapter.ATTR_URL,
			Chapter.ATTR_WEB_ID);
	@Autowired
	private WebRepository webRepository;
	
	private ChapterVO getChapterVO(Chapter chapter){
		ChapterVO result = null;
		try{
			result = ModelUtilProvider.getModelUtil().convertTo(chapter, ChapterVO.class);
			result.setObjectId(chapter.getInstanceid().toString());
			Web web = webRepository.findOne(new ObjectId(chapter.getWebId()));
			if(web!= null){
				WebVO webVO = ModelUtilProvider.getModelUtil().convertTo(web, WebVO.class);
				webVO.setObjectId(web.getInstanceid().toString());
				result.setWeb(webVO);
			}
		}catch (Exception e) {
			LOGGER.info("Chapter: {}", chapter.toString());
			LOGGER.error("An error when reading Chapter:", e);
		}
		return result;
	}
	@Override
	public GenericResponseObject findByManga(GenericRequestObject gRequest) {
		ChapterResponseObject response = new ChapterResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		String mangaId = null;
		try {
			ChapterRequestObject request = (ChapterRequestObject)gRequest;
			List<String> ids = request.getIds();
			if(ids == null || ids.isEmpty()){
				response.setMessage("Missing Manga to querry");
				response.setSuccess(false);
				return response;
			}
			mangaId = ids.get(0);
			List<ChapterVO> result = new ArrayList<>();
			Criteria criteria = Criteria.where(Chapter.ATTR_MANGA_ID).is(mangaId);
			String[] attributeNames = attrNames.toArray(new String[0]);
			List<Chapter> chapters = chapterRepository.searchByCriteria(criteria, Chapter.class, attributeNames);
			for (Chapter chapter : chapters) {
				ChapterVO cpVO = getChapterVO(chapter);
				if(cpVO != null){
					result.add(cpVO);
				}
			}
			response.setList(result);
		}catch (Exception e) {
			LOGGER.error("An error when reading Chapters by Manga {}:", mangaId, e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}
	@Override
	public GenericResponseObject findOne(GenericRequestObject gRequest) {
		ChapterResponseObject response = new ChapterResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			ChapterRequestObject request = (ChapterRequestObject)gRequest;
			List<String> ids = request.getIds();
			if(ids == null || ids.isEmpty()){
				response.setMessage("Missing Chapter to querry");
				response.setSuccess(false);
				return response;
			}
			List<ChapterVO> result = new ArrayList<>();
			Criteria criteria = Criteria.where(Chapter.ATTR_INSTANCE_ID).is(ids.get(0));
			List<String> listName = new ArrayList<>(attrNames);
			listName.add(Chapter.ATTR_IMAGES);
			String[] attributeNames = listName.toArray(new String[0]);
			List<Chapter> chapters = chapterRepository.searchByCriteria(criteria, Chapter.class, attributeNames);
			for (Chapter chapter : chapters) {
				ChapterVO cpVO = getChapterVO(chapter);
				if(cpVO != null){
					result.add(cpVO);
				}
			}
			response.setList(result);
		}catch (Exception e) {
			LOGGER.error("An error when reading Chapter", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

}
