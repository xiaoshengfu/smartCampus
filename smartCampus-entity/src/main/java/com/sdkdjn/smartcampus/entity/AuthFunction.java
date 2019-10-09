package com.sdkdjn.smartcampus.entity;

import java.util.HashSet;
import java.util.Set;

public class AuthFunction implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String code;
	private String page;
	private Set<AuthRole> authRoles = new HashSet<AuthRole>(0);

	public AuthFunction() {
	}

	public AuthFunction(String id, String name, String code, String page) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.page = page;
	}

	public AuthFunction(String id, String name, String code, String page, Set<AuthRole> authRoles) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.page = page;
		this.authRoles = authRoles;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Set<AuthRole> getAuthRoles() {
		return this.authRoles;
	}

	public void setAuthRoles(Set<AuthRole> authRoles) {
		this.authRoles = authRoles;
	}

}