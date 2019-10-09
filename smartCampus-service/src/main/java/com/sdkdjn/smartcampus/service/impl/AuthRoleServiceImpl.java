package com.sdkdjn.smartcampus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkdjn.smartcampus.dao.IAuthRoleDao;
import com.sdkdjn.smartcampus.entity.AuthRole;
import com.sdkdjn.smartcampus.service.IAuthRoleService;

@Service
@Transactional
public class AuthRoleServiceImpl implements IAuthRoleService {

	@Autowired
	private IAuthRoleDao authRoleDao;
	@Override
	public List<AuthRole> findAllAuthRole() {
		return authRoleDao.findAll();
	}
	@Override
	public AuthRole findAuthRoleById(String id) {
		return authRoleDao.findById(id);
	}
}
