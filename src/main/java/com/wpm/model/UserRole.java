package com.wpm.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRolePK id;

	//bi-directional one-to-one association to Role
	@ManyToOne
	@JoinColumn(name="idRole", insertable=false, updatable=false)
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser", insertable=false, updatable=false)
	private SqlUser user;

	public UserRole() {
	}

	public UserRole(UserRolePK userRolePK) {
		this.id = userRolePK; 
	}

	public UserRolePK getId() {
		return this.id;
	}

	public void setId(UserRolePK id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public SqlUser getUser() {
		return this.user;
	}

	public void setUser(SqlUser user) {
		this.user = user;
	}

}