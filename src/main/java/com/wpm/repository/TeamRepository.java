package com.wpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpm.model.Team;

public interface TeamRepository extends JpaRepository<Team,Integer>{

}
