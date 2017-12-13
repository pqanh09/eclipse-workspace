
package com.websystique.springmvc.service.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Job;
import com.websystique.springmvc.model.JobState;
import com.websystique.springmvc.model.USDTEnum;
import com.websystique.springmvc.model.UsdtHistory;
import com.websystique.springmvc.model.UsdtInput;
import com.websystique.springmvc.model.UsdtTotal;
import com.websystique.springmvc.repositories.UsdtHistoryRepository;
import com.websystique.springmvc.repositories.UsdtInputRepository;
import com.websystique.springmvc.repositories.UsdtTotalRepository;
import com.websystique.springmvc.request.UsdtInputRequestObject;
import com.websystique.springmvc.response.WSBittresResponse;

@Service("bittrexJobSchedulerHandler")
public class BittrexJobSchedulerHandler extends AbstractJobSchedulerHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(BittrexJobSchedulerHandler.class);

	@Autowired
	private UsdtHistoryRepository usdtHistoryRepository;

	@Autowired
	private UsdtTotalRepository usdtTotalRepository;
	
	
	@Autowired
	private UsdtInputRepository usdtInputRepository;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	

	
	
	@SuppressWarnings("unchecked")
	@Override
	public void process(JobDataMap jobParams) throws Exception {
		Object objId = jobParams.get(JobConstant.JOB_DATA_MAP_JOB_ID);
		// check job null
		if (objId == null) {
			LOGGER.error("No JobID to process.");
			return;
		}

		String jobId = objId.toString();
		LOGGER.info("Process Job {}:", jobId);
		Job jobDb = jobRepository.findOne(new ObjectId(jobId));

		if (jobDb == null || jobDb.getMangas().isEmpty() || StringUtils.isBlank(jobDb.getMangas().get(0))) {
			LOGGER.error("Not found job in db or No URL to poll");
			return;
		}
		
		UsdtInput usdtInput = usdtInputRepository.findAll().get(0);
		if(usdtInput == null){
			LOGGER.error("There is no Input");
			return;
		}

		HttpURLConnection conn = null;
		try {
			Date dtmp = new Date();
			Date date = new Date(dtmp.getYear(), dtmp.getMonth(), dtmp.getDate(), dtmp.getHours(), dtmp.getMinutes(),
					0);
			long timeId = date.getTime();

			URL url = new URL(jobDb.getMangas().get(0) + String.valueOf(timeId));
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				// TODO error conn.getResponseCode()
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line;
			StringBuilder responseStrBuilder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				responseStrBuilder.append(line);
			}
			conn.disconnect();

			JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

			JSONArray myResponse = jsonObject.getJSONArray("result");

			Iterator<Object> iterator = myResponse.iterator();

			Map<String, Double> map = new HashMap<>();
			while (iterator.hasNext()) {
				JSONObject element = (JSONObject) iterator.next();
				if (element.getJSONObject("Market").getString("BaseCurrency").equals("USDT")) {
					double lastPrice = element.getJSONObject("Summary").getDouble("Last");
					String marketName = element.getJSONObject("Summary").getString("MarketName");
					map.put(marketName, lastPrice);
				}
			}

			//save data to DB
			Date dt = new Date(dtmp.getYear(), dtmp.getMonth(), dtmp.getDate(), dtmp.getHours(), 0, 0);
			UsdtHistory usdtHistory = usdtHistoryRepository.findByTime(dt.getTime());
			if (usdtHistory == null) {
				usdtHistory = new UsdtHistory(dt.getTime());
			}
			List<Double> lastPrice = new ArrayList<>();
			
			lastPrice.add(map.get(USDTEnum.USDT_BTC.toString()));
			lastPrice.add(map.get(USDTEnum.USDT_BCC.toString()));
			lastPrice.add(map.get(USDTEnum.USDT_BTG.toString()));

			lastPrice.add(map.get(USDTEnum.USDT_DASH.toString()));
			lastPrice.add(map.get(USDTEnum.USDT_ETH.toString()));
			lastPrice.add(map.get(USDTEnum.USDT_ETC.toString()));

			lastPrice.add(map.get(USDTEnum.USDT_LTC.toString()));
			lastPrice.add(map.get(USDTEnum.USDT_XMR.toString()));
			lastPrice.add(map.get(USDTEnum.USDT_NEO.toString()));

			lastPrice.add(map.get(USDTEnum.USDT_OMG.toString()));
			lastPrice.add(map.get(USDTEnum.USDT_XRP.toString()));
			lastPrice.add(map.get(USDTEnum.USDT_ZEC.toString()));

			usdtHistory.getList().put(date.getMinutes(), lastPrice);
			//caculate percent
			
			List<Double> inputList = usdtInput.getList(); 
			List<Double> percent = new ArrayList<>(); 
			double total = 0;
			for (int i = 0; i < 12; i++) {
				//((obj.last - obj.input)*100/obj.input).toFixed(1);
				double pc = ((lastPrice.get(i) - inputList.get(i)) / inputList.get(i)) * 100;
				percent.add(pc);
				total = total + pc;
			}
			
			usdtHistoryRepository.safeSave(usdtHistory);
			
			//save total to DB
			UsdtTotal usdtTotal = usdtTotalRepository.findByTime(dt.getTime());
			if (usdtTotal == null) {
				usdtTotal = new UsdtTotal(dt.getTime());
			}
			
			usdtTotal.getList().put(String.valueOf(timeId), total / 12);
			
			usdtTotalRepository.safeSave(usdtTotal);
			// update job
			jobDb.setStatus(JobState.scheduled);

			jobRepository.safeSave(jobDb);
			
			
			// triger update UIs
			if (simpMessagingTemplate != null) {
				simpMessagingTemplate.convertAndSend("/topic/usdtMarkets", new WSBittresResponse(timeId, lastPrice, inputList, percent));
			} else {
				LOGGER.error("SimpMessagingTemplate is null.");
				return;
			}
		} catch (Exception e) {
			// TODO error
			LOGGER.error("An error when polling bittrex: ", e);
			return;
		} finally {
			if (conn != null) {
				LOGGER.warn("Try to disconnect");
				try {
					conn.disconnect();
				} catch (Exception e) {
					LOGGER.error("An error when trying to disconnect host ", e);
				}
			}
		}

	}

}
