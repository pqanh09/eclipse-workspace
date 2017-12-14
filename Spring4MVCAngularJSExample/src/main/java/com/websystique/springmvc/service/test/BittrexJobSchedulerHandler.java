
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
import java.util.Map.Entry;

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
import com.websystique.springmvc.model.UsdtEnum;
import com.websystique.springmvc.model.UsdtLastPrice;
import com.websystique.springmvc.model.UsdtJob;
import com.websystique.springmvc.model.UsdtTotal;
import com.websystique.springmvc.repositories.UsdtLastPriceRepository;
import com.websystique.springmvc.repositories.UsdtTotalRepository;
import com.websystique.springmvc.request.ObjectType;
import com.websystique.springmvc.response.WSBittresResponse;

@Service("bittrexJobSchedulerHandler")
public class BittrexJobSchedulerHandler extends AbstractJobSchedulerHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(BittrexJobSchedulerHandler.class);

	@Autowired
	private UsdtLastPriceRepository usdtLastPriceRepository;

	@Autowired
	private UsdtTotalRepository usdtTotalRepository;
	
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
		
		Object objInputs = jobParams.get(JobConstant.JOB_DATA_MAP_BITTREX_INPUTS);
		// check inputs null
		if (objInputs == null) {
			LOGGER.error("No input to process.");
			return;
		}
		List<Double> inputs = (List<Double>) objInputs;
		Object objCoins = jobParams.get(JobConstant.JOB_DATA_MAP_BITTREX_COINS);
		// check coins null
		if (objCoins == null) {
			LOGGER.error("No coins to process.");
			return;
		}
		List<Integer> coins = (List<Integer>) objCoins;
		String jobId = objId.toString();
		LOGGER.info("Process Job {}:", jobId);
		Job job = jobRepository.findOne(new ObjectId(jobId));
		UsdtJob jobDb = (UsdtJob) job;

		if (jobDb == null || StringUtils.isBlank(jobDb.getUrl())) {
			LOGGER.error("Not found job in db or No URL to poll");
			return;
		}
		

		HttpURLConnection conn = null;
		try {
			Date dtmp = new Date();
			Date date = new Date(dtmp.getYear(), dtmp.getMonth(), dtmp.getDate(), dtmp.getHours(), dtmp.getMinutes(),
					0);
			long timeId = date.getTime();

			URL url = new URL(jobDb.getUrl() + String.valueOf(timeId));
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
			UsdtLastPrice usdtHistory = usdtLastPriceRepository.findByTime(dt.getTime());
			if (usdtHistory == null) {
				usdtHistory = new UsdtLastPrice(dt.getTime());
			}
			List<Double> lPriceTmp = new ArrayList<>();
			Map<Integer, Double> lastPriceMap = new HashMap<>();
			
			lPriceTmp.add(map.get(UsdtEnum.USDT_BTC.toId()));
			lPriceTmp.add(map.get(UsdtEnum.USDT_BCC.toId()));
			lPriceTmp.add(map.get(UsdtEnum.USDT_BTG.toId()));

			lPriceTmp.add(map.get(UsdtEnum.USDT_DASH.toId()));
			lPriceTmp.add(map.get(UsdtEnum.USDT_ETH.toId()));
			lPriceTmp.add(map.get(UsdtEnum.USDT_ETC.toId()));

			lPriceTmp.add(map.get(UsdtEnum.USDT_LTC.toId()));
			lPriceTmp.add(map.get(UsdtEnum.USDT_XMR.toId()));
			lPriceTmp.add(map.get(UsdtEnum.USDT_NEO.toId()));

			lPriceTmp.add(map.get(UsdtEnum.USDT_OMG.toId()));
			lPriceTmp.add(map.get(UsdtEnum.USDT_XRP.toId()));
			lPriceTmp.add(map.get(UsdtEnum.USDT_ZEC.toId()));

			usdtHistory.getList().put(date.getMinutes(), lastPriceMap);
			
			//caculate percent
			List<Double> percent = new ArrayList<>();
			List<Double> lastPrices = new ArrayList<>();
			double total = 0;
			
			for(int i = 0; i < coins.size(); i++){
				int num = coins.get(i);
				double input = inputs.get(i);
				double lastPr = lPriceTmp.get(num);
				double pc = ((lastPr - input) / input) * 100;
				percent.add(pc);
				total = total + pc;
				lastPriceMap.put(num, lastPr);	
				lastPrices.add(lastPr);
			}
//			
//			for (Entry<Integer, Double> entry : inputMap.entrySet()) {
//				int key = entry.getKey();
//				double input = entry.getValue();
//				double lastPr = lastPrice.get(key);
//				double pc = ((lastPr - input) / input) * 100;
//				percent.add(pc);
//				total = total + pc;
//
//			}
			
			usdtLastPriceRepository.safeSave(usdtHistory);
			
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
				simpMessagingTemplate.convertAndSend("/topic/usdtMarkets", new WSBittresResponse(timeId, lastPrices, inputs, percent));
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
