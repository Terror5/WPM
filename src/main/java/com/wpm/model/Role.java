package com.wpm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idRole;

	private String title;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="roles")
	private List<SqlUser> users;

	//bi-directional one-to-one association to UserRole
	@OneToMany
	private List<UserRole> userRoles;

	public Role() {
	}

	public String getIdRole() {
		return this.idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SqlUser> getUsers() {
		return this.users;
	}

	public void setUsers(List<SqlUser> users) {
		this.users = users;
	}

	public List<UserRole> getUserRole() {
		return this.userRoles;
	}

	public void setUserRole(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}