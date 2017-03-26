package com.wpm.service;

import java.util.ArrayList;  
import java.util.Collection;  
import java.util.List;  
  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.security.core.GrantedAuthority;  
import org.springframework.security.core.authority.SimpleGrantedAuthority;  
import org.springframework.security.core.userdetails.User;  
import org.springframework.security.core.userdetails.UserDetails;  
import org.springframework.security.core.userdetails.UserDetailsService;  
import org.springframework.security.core.userdetails.UsernameNotFoundException;  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;  

import com.wpm.model.SqlUser;
import com.wpm.model.UserRole;
  
@Service  
@Transactional(readOnly=true)  
public class CustomUserDetailsService implements UserDetailsService {  
      
    @Autowired  
    private UserService userService;      
  
    public UserDetails loadUserByUsername(String login)  
            throws UsernameNotFoundException {  
          
    	SqlUser domainUser = userService.findById(login);
          
        boolean enabled = domainUser.getActive();  
        boolean accountNonExpired = true;  
        boolean credentialsNonExpired = true;  
        boolean accountNonLocked = true;  
  
        return new User(  
                domainUser.getIdUser(),   
                domainUser.getPwHash(),   
                enabled,   
                accountNonExpired,   
                credentialsNonExpired,   
                accountNonLocked,  
                getAuthorities(roleToArray(domainUser.getUserRoles()))  
        );  
    }  
      
    public String[] roleToArray(List<UserRole> roleList){
    	String[] perm = new String[roleList.size()];
    	int i = 0;
    	for(UserRole userRole : roleList){
    		perm[i] = userRole.getRole().getIdRole();
    		i++;
    	}
    	return perm;   	
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities(String[] permission) {  
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(permission));  
        return authList;  
    }  
      
    public List<String> getRoles(String[] permission) {  
  
        List<String> roles = new ArrayList<String>();  
  
        for(String role : permission){
        	switch (role) {
			case "ADMIN":
				roles.add("ROLE_ADMIN");
				if(!roles.equals("ROLE_USER")){
					roles.add("ROLE_USER");
				}
				break;
			case "USER":
				roles.add("ROLE_USER");
				break;	
			default:
				break;
			}
        	
        }
        return roles;  
    }  
      
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {  
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();  
          
        for (String role : roles) {  
            authorities.add(new SimpleGrantedAuthority(role));  
        }  
        return authorities;  
    }
      
}  