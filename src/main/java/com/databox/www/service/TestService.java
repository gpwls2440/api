package com.databox.www.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.databox.www.model.TestInfo;
import com.databox.www.common.ObjectConverter;
import com.databox.www.component.TmsComponent;
import com.databox.www.model.TmsResult;
import com.databox.www.tms.TMSRequestService;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@Slf4j
@Service
public class TestService {
	
	@Autowired
	private TmsComponent tmsComponent;

	public Map<String, Object> getTestData(String sDeptCd, String sDeptNm, String sDeptTp, String sUseYn) throws IOException, ParseException {
		
		String serviceName = "B1202Q";
		
		// TMS 전송용 변수 셋팅
		String sessid = "";
		String uid = "phj";
		
		TMSRequestService tmsService = new TMSRequestService(tmsComponent.getPools());
		Map<String, Object> inputItem = new HashMap<String, Object>();

		// 인풋데이터 입력
		inputItem.put("F01", sDeptCd);
		inputItem.put("F02", sDeptNm);
		inputItem.put("F03", sDeptTp);
		inputItem.put("F04", sUseYn);

		// 전송을 위한 폼 작업
		String input = ObjectConverter.mapToJson(inputItem);

		// log.debug("jsonInfo : " + input);

		TmsResult tmsResult = tmsService.request(serviceName, sessid, uid, input, 0, 0, " ");
		List<TestInfo> testInfoList =  new ArrayList<TestInfo>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		 if(tmsResult.isSuccess()) {
	        	log.debug("success : {}" , tmsResult.getOutput().trim());
	        	JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
	            JSONObject jsonObj = (JSONObject) jsonParser.parse(tmsResult.getOutput());
	            JSONArray jsonArr = (JSONArray) jsonObj.get("data");
	            
	            log.debug("=====data=====");
	            for(int i=0 ; i < jsonArr.size() ; i++){
	                JSONObject tempObj = (JSONObject) jsonArr.get(i);
	                
	                if(!tempObj.isEmpty()) {
	                	TestInfo testInfo = new TestInfo();
	                	testInfo.setS_dept_cd(tempObj.getAsString("F01"));
	                	testInfo.setS_dept_nm(tempObj.getAsString("F02")); 	
	                	testInfo.setS_dept_tp(tempObj.getAsString("F03"));
	                	testInfo.setS_dept_bsns_tp(tempObj.getAsString("F04"));
	                	
	                	testInfo.setS_brch_cd(tempObj.getAsString("F05"));
	                	testInfo.setS_brch_nm(tempObj.getAsString("F06")); 	
	                	testInfo.setS_clnt_cd(tempObj.getAsString("F07"));
	                	testInfo.setS_budgt_mngr_yn(tempObj.getAsString("F08"));
	                	
	                	testInfo.setS_budgt_grp_nm_1(tempObj.getAsString("F09"));
	                	testInfo.setS_budgt_grp_nm_2(tempObj.getAsString("F10"));
	                	testInfo.setS_use_yn(tempObj.getAsString("F11")); 	
	                	testInfo.setS_upd_wrk_id(tempObj.getAsString("F12"));
	                	testInfo.setS_upd_wrk_dtm(tempObj.getAsString("F13"));
	                	
	                	testInfoList.add(testInfo);
	                }
	                
	            }
	            resultMap.put("count", tmsResult.getMsgRecCnt());
	            resultMap.put("Msg", tmsResult.getMsgCtInfo());
	            resultMap.put("Code",  tmsResult.getMsgCode());
	            resultMap.put("list", testInfoList);
	            
	            
		} else {
			log.error("error_CODE : {} serviceName : {} inputItem : {} tmsResult : {}", tmsResult.getMsgCode(),
					serviceName, inputItem, tmsResult.getOutput().trim());
			// result = tmsResult.getMsgCode();
		}

		return resultMap;
		
	}
	
	
	
}
