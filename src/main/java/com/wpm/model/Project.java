package com.wpm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProject;

	@Temporal(TemporalType.DATE)
	private Date dateBegin;

	@Temporal(TemporalType.DATE)
	private Date dateEnd;

	private String description;

	private int iterationCycle;

	private String title;

	//bi-directional many-to-one association to Iteration
	@OneToMany(mappedBy="project")
	private List<Iteration> iterations;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="idTeam")
	private Team team;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="project")
	private List<SqlUser> users;

	//bi-directional many-to-one association to UserProjectRole
	@OneToMany(mappedBy="project")
	private List<UserProjectRole> userProjectRoles;

	//bi-directional many-to-one association to Workitem
	@OneToMany(mappedBy="project")
	private List<Workitem> workitems;

	public Project() {
	}
	
	public Project(int idProject) {
		this.idProject = idProject;
	}

	public int getIdProject() {
		return this.idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public Date getDateBegin() {
		return this.dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIterationCycle() {
		return this.iterationCycle;
	}

	public void setIterationCycle(int iterationCycle) {
		this.iterationCycle = iterationCycle;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Iteration> getIterations() {
		return this.iterations;
	}

	public void setIterations(List<Iteration> iterations) {
		this.iterations = iterations;
	}

	public Iteration addIteration(Iteration iteration) {
		getIterations().add(iteration);
		iteration.setProject(this);

		return iteration;
	}

	public Iteration removeIteration(Iteration iteration) {
		getIterations().remove(iteration);
		iteration.setProject(null);

		return iteration;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<SqlUser> getUsers() {
		return this.users;
	}

	public void setUsers(List<SqlUser> users) {
		this.users = users;
	}

	public SqlUser addUser(SqlUser user) {
		getUsers().add(user);
		user.setProject(this);

		return user;
	}

	public SqlUser removeUser(SqlUser user) {
		getUsers().remove(user);
		user.setProject(null);

		return user;
	}

	public List<UserProjectRole> getUserProjectRoles() {
		return this.userProjectRoles;
	}

	public void setUserProjectRoles(List<UserProjectRole> userProjectRoles) {
		this.userProjectRoles = userProjectRoles;
	}

	public UserProjectRole addUserProjectRole(UserProjectRole userProjectRole) {
		getUserProjectRoles().add(userProjectRole);
		userProjectRole.setProject(this);

		return userProjectRole;
	}

	public UserProjectRole removeUserProjectRole(UserProjectRole userProjectRole) {
		getUserProjectRoles().remove(userProjectRole);
		userProjectRole.setProject(null);

		return userProjectRole;
	}

	public List<Workitem> getWorkitems() {
		return this.workitems;
	}

	public void setWorkitems(List<Workitem> workitems) {
		this.workitems = workitems;
	}

	public Workitem addWorkitem(Workitem workitem) {
		getWorkitems().add(workitem);
		workitem.setProject(this);

		return workitem;
	}

	public Workitem removeWorkitem(Workitem workitem) {
		getWorkitems().remove(workitem);
		workitem.setProject(null);

		return workitem;
	}

}