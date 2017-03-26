package com.wpm.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class CustomUrlService {
	
	public int formatPathToInt(String path,String requested){
		
		int currentId = -1;
		String buffer = "";
		
		if(!path.contains(requested)){
			return currentId;
		}

		buffer = path.substring(path.indexOf(requested));
		
		if(buffer.contains("&")){
			buffer = buffer.substring(buffer.indexOf("=")+1,buffer.indexOf("&"));	
			
		} else {	
			buffer = buffer.substring(buffer.indexOf("=")+1);	
		}
		
		try {
			currentId = Integer.parseInt(buffer);
		} catch (Exception e) {
			return currentId;
		}
		return currentId;
	}
	
	public String formatPathToString(String path,String requested){
		
		String buffer = "";
		
		if(!path.contains(requested)){
			return null;
		}

		buffer = path.substring(path.indexOf(requested));
		
		if(buffer.contains("&")){
			buffer = buffer.substring(buffer.indexOf("=")+1,buffer.indexOf("&"));	
			
		} else {	
			buffer = buffer.substring(buffer.indexOf("=")+1);	
		}	
		return buffer;
		
	}
	
	public HashMap<String,Integer> formatPathToMap(String path){
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		String[] all = path.split("&");
		
		for(String part : all){
			String[] parts = part.split("=");
			try {
				map.put(parts[0], Integer.valueOf(parts[1]));
			} catch (Exception e) {
				return null;
			}
		}		
		return map;
	}
	
	public HashMap<String,String> formatPathToMapString(String path){
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		String[] all = path.split("&");
		
		for(String part : all){
			String[] parts = part.split("=");
			map.put(parts[0], parts[1]);
		}		
		return map;
	}
}