package com.wpm.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.wpm.model.Iteration;


public class IterationFormatter implements Formatter<Iteration>
{
	
		@Override
		public String print(Iteration iteration, Locale locale) {
			return String.valueOf(iteration.getIdIteration());
		}

		@Override
		public Iteration parse(String idIteration, Locale locale) throws ParseException {
			Iteration iteration = new Iteration();
			int id  = -1;
			if(!idIteration.contains("-")){
				id = Integer.parseInt(idIteration);	
			}
			iteration.setIdIteration(id);
			return iteration;
		}

}
