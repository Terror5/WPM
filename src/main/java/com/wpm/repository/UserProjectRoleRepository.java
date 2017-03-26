package com.wpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpm.model.UserProjectRole;
import com.wpm.model.UserProjectRolePK;

public interface UserProjectRoleRepository extends JpaRepository<UserProjectRole, UserProjectRolePK> {

}
