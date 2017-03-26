package com.wpm.formatter;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import com.wpm.model.Workitem;

public class WorkitemFormatter implements Formatter<Workitem> 
{
	@Override
	public String print(Workitem workitem, Locale locale) {
		return String.valueOf(workitem.getIdWorkitem());
	}

	@Override
	public Workitem parse(String idWorkitem, Locale locale) throws ParseException {
		Workitem workitem = new Workitem();
		int id  = -1;
		if(!idWorkitem.contains("-")){
			id = Integer.parseInt(idWorkitem);	
		}
		workitem.setIdWorkitem(id);
		return workitem;
	}

}
