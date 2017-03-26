package com.wpm.formatter;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class ByteFormatter implements Formatter<Byte> 
{
	@Override
	public String print(Byte b, Locale locale) {
		return String.valueOf(b);
	}

	@Override
	public Byte parse(String s, Locale locale) throws ParseException {
		int i = Integer.parseInt(s);
		return (byte) i;
	}

}
