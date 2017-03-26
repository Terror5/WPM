package com.wpm.formatter;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import com.wpm.model.Project;


public class ProjectFormatter implements Formatter<Project> {

	@Override
	public String print(Project project, Locale locale) {
		return String.valueOf(project.getIdProject());
	}

	@Override
	public Project parse(String idProject, Locale locale) throws ParseException {
		Project project = new Project();
		int id  = -1;
		if(!idProject.contains("-")){
			id = Integer.parseInt(idProject);	
		}
		project.setIdProject(id);
		return project;
	}

}
