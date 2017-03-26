package com.wpm.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.wpm.model.Openuprole;


@Component
public class OpenuproleFormatter implements Formatter<Openuprole> {
	
	@Override
	public String print(Openuprole openuprole, Locale locale) {
		return String.valueOf(openuprole.getIdOpenUpRole());
	}

	@Override
	public Openuprole parse(String formatted, Locale locale) throws ParseException {
		Openuprole openuprole = new Openuprole();
		openuprole.setIdOpenUpRole((String)formatted);
		return openuprole;
	}

}
