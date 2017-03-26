package com.wpm.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.wpm.model.SqlUser;

@Component
public class UserFormatter implements Formatter<SqlUser> {
	
	@Override
	public String print(SqlUser user, Locale locale) {
		return String.valueOf(user.getIdUser());
	}

	@Override
	public SqlUser parse(String formatted, Locale locale) throws ParseException {
		return new SqlUser(formatted);
	}

}
