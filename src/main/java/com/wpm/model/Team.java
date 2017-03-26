package com.wpm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTeam;

	private String description;

	private String title;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="team", fetch=FetchType.LAZY)
	private List<Project> projects;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="team", fetch=FetchType.LAZY)
	private List<SqlUser> users;

	public Team() {
	}

	public int getIdTeam() {
		return this.idTeam;
	}

	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setTeam(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setTeam(null);

		return project;
	}

	public List<SqlUser> getUsers() {
		return this.users;
	}

	public void setUsers(List<SqlUser> users) {
		this.users = users;
	}

	public SqlUser addUser(SqlUser user) {
		getUsers().add(user);
		user.setTeam(this);

		return user;
	}

	public SqlUser removeUser(SqlUser user) {
		getUsers().remove(user);
		user.setTeam(null);

		return user;
	}

}