package com.sdkdjn.smartcampus.service;

import java.util.List;

import com.sdkdjn.smartcampus.entity.AuthRole;

public interface IAuthRoleService {

	public List<AuthRole> findAllAuthRole();

	public AuthRole findAuthRoleById(String id);

}
