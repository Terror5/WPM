package com.wpm.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.encoding.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.*;

import com.wpm.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(userDetailsService())
        		.passwordEncoder(passwordEncoder());
    }
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/resources/**");
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/admin/**").hasRole("ADMIN")
            	.antMatchers("/secured/**").hasRole("USER")
            	.antMatchers("/public/**").permitAll()
            	.antMatchers("/index.jsp").permitAll()
                .anyRequest().authenticated()
                .and()                
            .formLogin()
                .loginPage("/public/login").permitAll()
                .defaultSuccessUrl("/secured/home")
        		.and()
        	.logout()
        		.permitAll();
    }
    
	@Bean
	protected UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}
}
