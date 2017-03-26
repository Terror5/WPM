package com.wpm.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the user_role database table.
 * 
 */
@Embeddable
public class UserRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String idRole;

	@Column(insertable=false, updatable=false)
	private String idUser;

	public UserRolePK() {
	}
	
	public UserRolePK(String idRole, String idUser) {
		this.idRole = idRole;
		this.idUser = idUser;
	}
	public String getIdRole() {
		return this.idRole;
	}
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	public String getIdUser() {
		return this.idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserRolePK)) {
			return false;
		}
		UserRolePK castOther = (UserRolePK)other;
		return 
			this.idRole.equals(castOther.idRole)
			&& this.idUser.equals(castOther.idUser);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idRole.hashCode();
		hash = hash * prime + this.idUser.hashCode();
		
		return hash;
	}
}