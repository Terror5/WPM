package com.wpm.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.wpm.model.SqlUser;
import com.wpm.service.UserService;

@Component
public class RegistrationFormatter implements Formatter<SqlUser> {

	@Autowired
	private UserService userService;
	
	@Override
	public String print(SqlUser user, Locale locale) {
		return String.valueOf(user.getIdUser());
	}

	@Override
	public SqlUser parse(String formatted, Locale locale) throws ParseException {
		return new SqlUser(formatted);
	}

}