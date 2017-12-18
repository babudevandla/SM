package com.sm.portal.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("inMemoryCache")
public class InMemoryCache {	
	
	public static HashMap<String,String> propertyMap = null;
	public static Map<Integer,Integer> premiums = null;
	
	public InMemoryCache() {
		
	}
	
	public static HashMap<String, String> getPropertyMap(){
		return propertyMap;
	}
	public static void setPropertyMap(HashMap<String, String> properties){
		propertyMap = properties;
	}
	
	public static  String getCaProperty(String key){
		return propertyMap.get(key)==null?"":propertyMap.get(key);
	}
	public static  void setCaProperty(String key,String value){
		propertyMap.put(key,value);
	}

	public static Map<Integer, Integer> getPremiums() {
		return premiums;
	}

	public static void setPremiums(Map<Integer, Integer> premiums) {
		InMemoryCache.premiums = premiums;
	}
	
}
