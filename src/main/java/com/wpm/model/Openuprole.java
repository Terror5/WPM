package com.wpm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the openuprole database table.
 * 
 */
@Entity
@NamedQuery(name="Openuprole.findAll", query="SELECT o FROM Openuprole o")
public class Openuprole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idOpenUpRole;

	private String title;

	//bi-directional many-to-one association to UserProjectRole
	@OneToMany(mappedBy="openuprole")
	private List<UserProjectRole> userProjectRoles;

	public Openuprole() {
	}

	public String getIdOpenUpRole() {
		return this.idOpenUpRole;
	}

	public void setIdOpenUpRole(String idOpenUpRole) {
		this.idOpenUpRole = idOpenUpRole;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<UserProjectRole> getUserProjectRoles() {
		return this.userProjectRoles;
	}

	public void setUserProjectRoles(List<UserProjectRole> userProjectRoles) {
		this.userProjectRoles = userProjectRoles;
	}

	public UserProjectRole addUserProjectRole(UserProjectRole userProjectRole) {
		getUserProjectRoles().add(userProjectRole);
		userProjectRole.setOpenuprole(this);

		return userProjectRole;
	}

	public UserProjectRole removeUserProjectRole(UserProjectRole userProjectRole) {
		getUserProjectRoles().remove(userProjectRole);
		userProjectRole.setOpenuprole(null);

		return userProjectRole;
	}

}