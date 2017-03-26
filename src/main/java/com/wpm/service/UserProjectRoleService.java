package com.wpm.service;

import java.util.List;

import com.wpm.model.UserProjectRole;
import com.wpm.model.UserProjectRolePK;


public interface UserProjectRoleService {

	public UserProjectRole create(UserProjectRole userProjectRole);
    public UserProjectRole delete(UserProjectRolePK id);
    public List<UserProjectRole> findAll();
    public UserProjectRole update(UserProjectRole userProjectRole);
    public UserProjectRole findById(UserProjectRolePK id);
    public List<UserProjectRole> createBatch(Iterable<UserProjectRole> userProjectRoleBatch);

}
