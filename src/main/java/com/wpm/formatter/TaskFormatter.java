package com.wpm.formatter;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import com.wpm.model.Task;


public class TaskFormatter implements Formatter<Task> {

	@Override
	public String print(Task task, Locale locale) {
		return String.valueOf(task.getIdTask());
	}

	@Override
	public Task parse(String idTask, Locale locale) throws ParseException {
		Task task = new Task();
		int id  = -1;
		if(!idTask.contains("-")){
			id = Integer.parseInt(idTask);	
		}
		task.setIdTask(id);
		return task;
	}

}
