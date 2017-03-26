package com.wpm.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="SqlUser.findAll", query="SELECT u FROM SqlUser u")
public class SqlUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idUser;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active;

	private String eMail;

	private String firstName;

	private String lastName;
	
	private String pwHash;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="user")
	private List<Task> tasks;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="prefProject")
	private Project project;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="user_role"
		, joinColumns={
			@JoinColumn(name="idUser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idRole")
			}
		)
	private List<Role> roles;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="idTeam")
	private Team team;

	//bi-directional many-to-one association to UserProjectRole
	@OneToMany(mappedBy="user")
	private List<UserProjectRole> userProjectRoles;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;

	@Transient
	private String fullName;
	
	public SqlUser() {
	}
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public SqlUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String geteMail() {
		return this.eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPwHash() {
		return this.pwHash;
	}

	public void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setUser(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setUser(null);

		return task;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<UserProjectRole> getUserProjectRoles() {
		return this.userProjectRoles;
	}

	public void setUserProjectRoles(List<UserProjectRole> userProjectRoles) {
		this.userProjectRoles = userProjectRoles;
	}

	public UserProjectRole addUserProjectRole(UserProjectRole userProjectRole) {
		getUserProjectRoles().add(userProjectRole);
		userProjectRole.setUser(this);

		return userProjectRole;
	}

	public UserProjectRole removeUserProjectRole(UserProjectRole userProjectRole) {
		getUserProjectRoles().remove(userProjectRole);
		userProjectRole.setUser(null);

		return userProjectRole;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

}