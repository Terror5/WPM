package com.wpm.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.wpm.model.Team;
import com.wpm.service.TeamService;

@Component
public class TeamFormatter implements Formatter<Team> {

	@Autowired
	private TeamService teamService;
	
	@Override
	public String print(Team team, Locale locale) {
		return String.valueOf(team.getIdTeam());
	}

	@Override
	public Team parse(String idTeam, Locale locale) throws ParseException {
		Team team = new Team();
		int id  = -1;
		if(!idTeam.contains("-")){
			id = Integer.parseInt(idTeam);	
		}
		team.setIdTeam(id);
		return team;
	}

}
