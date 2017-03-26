package com.wpm.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.wpm.service.UserService;

public class TeamUsers {
	
	private Map<String, String> userMap = new HashMap<String,String>();
	
	@Autowired
	private UserService userService;
	
	public TeamUsers(){
		
	}
	
	public TeamUsers(List<SqlUser> users){
   		for(SqlUser user : users){
   			userMap.put(String.valueOf(user.getIdUser()),user.getFirstName() + " " + user.getLastName());
   		}
	}
	
	public void setTeamUsers(Map<String,String> teamUsersMap){
		userMap = teamUsersMap;
	}
	
	public Map<String,String> getTeamUsers(){
		return this.userMap;
	}
	
	public List<SqlUser> getEntities(){
		Set<String> idSet = userMap.keySet();
		Collection<Integer> userIds = new ArrayList<Integer>();
		for(String id : idSet){
			userIds.add(Integer.parseInt(id));
		}										
		return userService.findByIdUserIn(userIds);
		
	}
}
