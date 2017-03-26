package com.wpm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpm.model.Openuprole;
import com.wpm.repository.OpenUpRoleRepository;



@Service
public class OpenUpRoleServiceImp implements OpenUpRoleService{

	@Resource
	private OpenUpRoleRepository openUpRoleRepository;
	

	@Transactional
	public List<Openuprole> findAll() {
		return openUpRoleRepository.findAll();
	}
}