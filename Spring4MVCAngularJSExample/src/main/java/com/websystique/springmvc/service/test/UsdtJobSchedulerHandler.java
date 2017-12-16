
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
import com.websystique.springmvc.model.JobType;
import com.websystique.springmvc.model.UsdtEnum;
import com.websystique.springmvc.model.UsdtJob;
import com.websystique.springmvc.model.UsdtLastPrice;
import com.websystique.springmvc.model.UsdtTotal;
import com.websystique.springmvc.repositories.UsdtLastPriceRepository;
import com.websystique.springmvc.repositories.UsdtTotalRepository;
import com.websystique.springmvc.response.WSUpdateMarketResponse;

@Service("usdtJobSchedulerHandler")
public class UsdtJobSchedulerHandler extends AbstractJobSchedulerHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsdtJobSchedulerHandler.class);

	@Autowired
	private UsdtLastPriceRepository usdtLastPriceRepository;

	@Autowired
	private UsdtTotalRepository usdtTotalRepository;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	

	
	private void getLastPrice(UsdtJob jobDb){
		// check url
		if(StringUtils.isBlank(jobDb.getUrl())){
			LOGGER.error("No URL to process.");
			return;
		}

		HttpURLConnection conn = null;
		try {
			Date dtmp = new Date();
			long timeId = new Date(dtmp.getYear(), dtmp.getMonth(), dtmp.getDate(), dtmp.getHours(), dtmp.getMinutes(), 0).getTime();

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
			Date dtHour = new Date(dtmp.getYear(), dtmp.getMonth(), dtmp.getDate(), dtmp.getHours(), 0, 0);
			UsdtLastPrice usdtLastPrice = usdtLastPriceRepository.findByTime(dtHour.getTime());
			if (usdtLastPrice == null) {
				usdtLastPrice = new UsdtLastPrice(dtHour.getTime());
			}
			List<Double> lastPrices = new ArrayList<>();
			
			lastPrices.add(map.get(UsdtEnum.USDT_BTC.toId()));
			lastPrices.add(map.get(UsdtEnum.USDT_BCC.toId()));
			lastPrices.add(map.get(UsdtEnum.USDT_BTG.toId()));

			lastPrices.add(map.get(UsdtEnum.USDT_DASH.toId()));
			lastPrices.add(map.get(UsdtEnum.USDT_ETH.toId()));
			lastPrices.add(map.get(UsdtEnum.USDT_ETC.toId()));

			lastPrices.add(map.get(UsdtEnum.USDT_LTC.toId()));
			lastPrices.add(map.get(UsdtEnum.USDT_XMR.toId()));
			lastPrices.add(map.get(UsdtEnum.USDT_NEO.toId()));

			lastPrices.add(map.get(UsdtEnum.USDT_OMG.toId()));
			lastPrices.add(map.get(UsdtEnum.USDT_XRP.toId()));
			lastPrices.add(map.get(UsdtEnum.USDT_ZEC.toId()));

			usdtLastPrice.getList().put(timeId, lastPrices);
			
			usdtLastPriceRepository.safeSave(usdtLastPrice);
			
			jobDb.setStatus(JobState.scheduled);

			jobRepository.safeSave(jobDb);
			
		} catch (Exception e) {
			// TODO error
			LOGGER.error("An error when getting last price: ", e);
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
	private void updateMarket(UsdtJob jobDb){
		// check coins
		if(jobDb.getCoins().isEmpty()){
			LOGGER.error("No coins to process.");
			return;
		}
		List<Integer> coins = jobDb.getCoins();
		// check inputs
		if(jobDb.getInputs().isEmpty()){
			LOGGER.error("No inputs to process.");
			return;
		}
		List<Double> inputs = jobDb.getInputs();
		
		
		
		
		try {
			Date dtmp = new Date();
			long timeId = new Date(dtmp.getYear(), dtmp.getMonth(), dtmp.getDate(), dtmp.getHours(), dtmp.getMinutes(), 0).getTime();
			
			//save data to DB
			Date dtHour = new Date(dtmp.getYear(), dtmp.getMonth(), dtmp.getDate(), dtmp.getHours(), 0, 0);
			UsdtLastPrice usdtLastPrice = usdtLastPriceRepository.findByTime(dtHour.getTime());
			if (usdtLastPrice == null) {
				LOGGER.error("No UsdtLastPrice to process");
				return;
			}
			int minute = dtmp.getMinutes();
			List<Double> lastPrices = usdtLastPrice.getList().get(timeId);
			if(lastPrices == null || lastPrices.isEmpty()){
				LOGGER.error("No Last price  of time {} to process", timeId);
				return;
			}
			//caculate percent
			List<Double> percent = new ArrayList<>();
			List<Double> lastPricesByCoins = new ArrayList<>();
			double total = 0;
			
			for(int i = 0; i < coins.size(); i++){
				int num = coins.get(i);
				double input = inputs.get(i);
				double lastPr = lastPrices.get(num);
				double pc = ((lastPr - input) / input) * 100;
				percent.add(pc);
				lastPricesByCoins.add(lastPr);
				total = total + pc;
			}
			
			//save total to DB
			UsdtTotal usdtTotal = usdtTotalRepository.findByTime(dtHour.getTime());
			if (usdtTotal == null) {
				usdtTotal = new UsdtTotal(dtHour.getTime());
			}
			usdtTotal.setJobId(jobDb.getInstanceid().toString());
			usdtTotal.getList().put(timeId, total / coins.size());
			
			usdtTotalRepository.safeSave(usdtTotal);
			
			// update job
			jobDb.setStatus(JobState.scheduled);
			
			jobRepository.safeSave(jobDb);
			
			// triger update UIs
			if (simpMessagingTemplate != null) {
				simpMessagingTemplate.convertAndSend("/topic/usdtMarkets", new WSUpdateMarketResponse(timeId, lastPricesByCoins, percent, total / coins.size()));
			} else {
				LOGGER.error("SimpMessagingTemplate is null.");
				return;
			}
		} catch (Exception e) {
			// TODO error
			LOGGER.error("An error when polling bittrex: ", e);
			return;
		} 
	}
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
		Job job = jobRepository.findOne(new ObjectId(jobId));
		UsdtJob jobDb = (UsdtJob) job;

		if (jobDb == null) {
			LOGGER.error("Not found job in db ");
			return;
		}
		if(JobType.GetLastPrice.equals(jobDb.getType())){
			getLastPrice(jobDb);
		} else if(JobType.UpdateMarket.equals(jobDb.getType())){
			updateMarket(jobDb);
		} else {
			LOGGER.error("Not support {} Job", jobDb.getType());
			return;
		}
	}

}
