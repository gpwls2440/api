package com.databox.www.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.databox.www.model.TestInfo;
import com.databox.www.service.TestService;

import net.minidev.json.parser.ParseException;

@Controller
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value="/", method= RequestMethod.GET)
    @ResponseBody
    public String home() throws IOException{
    	
		String home = "home";
		
		return home;
    }
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> test(
			@RequestParam(value = "s_dept_cd", required = false, defaultValue = "*") String sDeptCd,
			@RequestParam(value = "s_dept_nm", required = false, defaultValue = "") String sDeptNm,
			@RequestParam(value = "s_dept_tp", required = false, defaultValue = "*") String sDeptTp,
			@RequestParam(value = "s_use_yn", required = false, defaultValue = "*") String sUseYn) throws IOException, ParseException{
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = (Map<String, Object>) testService.getTestData(sDeptCd, sDeptNm, sDeptTp, sUseYn);
		
		
		return resultMap;
	}
}
