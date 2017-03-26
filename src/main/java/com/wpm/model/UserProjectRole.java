package com.wpm.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the user_project_role database table.
 * 
 */
@Entity
@Table(name="user_project_role")
@NamedQuery(name="UserProjectRole.findAll", query="SELECT u FROM UserProjectRole u")
public class UserProjectRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserProjectRolePK id;

	//bi-directional one-to-one association to Openuprole
	@ManyToOne
	@JoinColumn(name="idOpenUpRole", insertable=false, updatable=false)
	private Openuprole openuprole;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="idProject", insertable=false, updatable=false)
	private Project project;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser", insertable=false, updatable=false)
	private SqlUser user;

	public UserProjectRole() {
	}

	public UserProjectRolePK getId() {
		return this.id;
	}

	public void setId(UserProjectRolePK id) {
		this.id = id;
	}

	public Openuprole getOpenuprole() {
		return this.openuprole;
	}

	public void setOpenuprole(Openuprole openuprole) {
		this.openuprole = openuprole;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public SqlUser getUser() {
		return this.user;
	}

	public void setUser(SqlUser user) {
		this.user = user;
	}

}