
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
import com.websystique.springmvc.repositories.UsdtHistoryRepository;
import com.websystique.springmvc.response.WSBittresResponse;

@Service("bittrexJobSchedulerHandler")
public class BittrexJobSchedulerHandler extends AbstractJobSchedulerHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(BittrexJobSchedulerHandler.class);

	@Autowired
	UsdtHistoryRepository usdtRepository;

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

			Date dt = new Date(dtmp.getYear(), dtmp.getMonth(), dtmp.getDate(), dtmp.getHours(), 0, 0);
			UsdtHistory usdt = usdtRepository.findByTime(dt.getTime());
			if (usdt == null) {
				usdt = new UsdtHistory(dt.getTime());
			}
			List<Double> list = new ArrayList<>();
			list.add(map.get(USDTEnum.USDT_BTC.toString()));
			list.add(map.get(USDTEnum.USDT_BCC.toString()));
			list.add(map.get(USDTEnum.USDT_BTG.toString()));

			list.add(map.get(USDTEnum.USDT_DASH.toString()));
			list.add(map.get(USDTEnum.USDT_ETH.toString()));
			list.add(map.get(USDTEnum.USDT_ETC.toString()));

			list.add(map.get(USDTEnum.USDT_LTC.toString()));
			list.add(map.get(USDTEnum.USDT_XMR.toString()));
			list.add(map.get(USDTEnum.USDT_NEO.toString()));

			list.add(map.get(USDTEnum.USDT_OMG.toString()));
			list.add(map.get(USDTEnum.USDT_XRP.toString()));
			list.add(map.get(USDTEnum.USDT_ZEC.toString()));

			usdt.getList().put(date.getMinutes(), list);

			usdtRepository.safeSave(usdt);
			// update job
			jobDb.setStatus(JobState.scheduled);

			jobRepository.safeSave(jobDb);
			// triger update UIs
			if (simpMessagingTemplate != null) {
				simpMessagingTemplate.convertAndSend("/topic/usdtMarkets", new WSBittresResponse(timeId, list));
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
