package com.wpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wpm.model.SqlUser;
import com.wpm.model.UserRole;
import com.wpm.model.UserRolePK;
import com.wpm.service.UserService;

@Service
public class NewUsersService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	private String[] names = new String[]{"test","funger","stefan","maike","user"};
	
	public void createAdminAccount(){
		if(userService.findById("admin") == null){
			createUser("admin","ADMIN");
		}
	}
	
	public int createUsers(){
		int i = 0;
		String roleString = "USER";
		
		for(String name : names){
			
			if(name.equals("funger")){
				roleString = "ADMIN";
			}
			else{
				roleString = "USER";
			}
			if(userService.findById(name) != null){
				try {				
					createUser(name,roleString);
					i += 1;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return i;
	}
	
	public void setTestNames(String[] testNames){
		names = testNames;
	}
	
	
	private void createUser(String name, String roleID){		
    	SqlUser user = new SqlUser();
		user.setIdUser(name);
		user.setFirstName(name);
		user.setLastName(name);
		user.seteMail(name + "@"+ name +".de");
		user.setActive(true);
		user.setPwHash(encodedPassword(name));
		userService.create(user);
		userRoleService.create(new UserRole(new UserRolePK(roleID,name)));			
	}
	
    private String encodedPassword(String rawPassword)
    {
    	PasswordEncoder encoder = new StandardPasswordEncoder();
    	return encoder.encode(rawPassword);
    	
    }
}
