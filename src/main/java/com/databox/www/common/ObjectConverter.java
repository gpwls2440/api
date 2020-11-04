package com.databox.www.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class ObjectConverter {
	
	/**
	 * 일반객체를 json으로 변환
	 * @param value
	 * @return
	 */
	static public String objectToJson(Object value){
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
		try {
			return mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
//			log.info("printStackTrace",e);
			return "";
		}
	}
	static public String mapToJson(Map<?,?> value){
		String tmp = ObjectConverter.objectToJson(value);
		String first = "{\"data\":[";
		String last = "]}";
		String result = first + tmp + last;
		return result;
	}
	
	static public <T> String listToJson(TypeReference<List<T>> valueTypeRef){
		return ObjectConverter.objectToJson(valueTypeRef);
	}
	
	/**
	 *  json(String)를 List<객체>로 변환
	 * @param content
	 * @param valueTypeRef
	 * @return
	 */
	static public <T> List<T> jsonToList(String content, TypeReference<List<T>> valueTypeRef){
		return ObjectConverter.jsonToList(content, valueTypeRef, false);
	}
	
	/**
	 * 
	 * @param content
	 * @param valueTypeRef
	 * @param ignoreUnknown : 변환할 수 없는 객체 존재시 처리방법
	 * @return
	 */
	static public <T> List<T> jsonToList(String content, TypeReference<List<T>> valueTypeRef, boolean ignoreUnknown){
		ObjectMapper mapper=new ObjectMapper();
		if(ignoreUnknown){
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
		try {
			return mapper.readValue(content, valueTypeRef);
		} catch (IOException e) {
//			log.info("printStackTrace",e);
			return null;
		}
	}
	static public <T> T jsonToObject(String content ,  Class<T> toValueType){
		ObjectMapper mapper=new ObjectMapper();
		try {
			return (T) mapper.readValue(content, toValueType);
		} catch (IOException e) {
//			log.info("printStackTrace",e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	static public <T> T jsonToMap(String content ){
		return (T) ObjectConverter.jsonToObject(content, Map.class);
	}
	
//	<T> T convertValue(Object fromValue, Class<T> toValueType)
	static public Map<?, ?> objectToMap (Object fromValue){
		ObjectMapper mapper=new ObjectMapper();
		return  mapper.convertValue(fromValue, Map.class);
	}
	
	/**
	 * 일반 객체를 원하는 List<객체>로 변환
	 * @param fromValue
	 * @param toValueType
	 * @return
	 */
	static public <T> List<T> objectToList(Object fromValue, TypeReference<List<T>> toValueType){
		ObjectMapper mapper=new ObjectMapper();
		return mapper.convertValue(fromValue,  toValueType);
		
	}
	
	static public <T> T objectToObject(Object fromValue, Class<T> toValueType){
		ObjectMapper mapper=new ObjectMapper();
		return mapper.convertValue(fromValue, toValueType);
	}
	
	static public Map<?, ?> stringToMap(String content){
		ObjectMapper mapper=new ObjectMapper();
		try {
			return mapper.readValue(content, Map.class);
		} catch (IOException e) {
//			log.info("printStackTrace",e);
			return null;
		}
	}


}
