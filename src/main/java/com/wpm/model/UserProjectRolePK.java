package com.wpm.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_project_role database table.
 * 
 */
@Embeddable
public class UserProjectRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String idOpenUpRole;

	@Column(insertable=false, updatable=false)
	private int idProject;

	@Column(insertable=false, updatable=false)
	private String idUser;

	public UserProjectRolePK() {
	}
	
	public UserProjectRolePK(String idOpenUpRole, int idProject, String idUser) {
		this.idOpenUpRole = idOpenUpRole;
		this.idProject = idProject;
		this.idUser = idUser;
	}
	
	public String getIdOpenUpRole() {
		return this.idOpenUpRole;
	}
	public void setIdOpenUpRole(String idOpenUpRole) {
		this.idOpenUpRole = idOpenUpRole;
	}
	public int getIdProject() {
		return this.idProject;
	}
	public void setIdProject(int idProject) {
		this.idProject = idProject;
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
		if (!(other instanceof UserProjectRolePK)) {
			return false;
		}
		UserProjectRolePK castOther = (UserProjectRolePK)other;
		return 
			this.idOpenUpRole.equals(castOther.idOpenUpRole)
			&& (this.idProject == castOther.idProject)
			&& this.idUser.equals(castOther.idUser);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idOpenUpRole.hashCode();
		hash = hash * prime + this.idProject;
		hash = hash * prime + this.idUser.hashCode();
		
		return hash;
	}
}