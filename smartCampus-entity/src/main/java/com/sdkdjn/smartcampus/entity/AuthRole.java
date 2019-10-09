package com.sdkdjn.smartcampus.entity;

import java.util.HashSet;
import java.util.Set;

public class AuthRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String code;
	private String information;
	private Set<User> users = new HashSet<User>(0);
	private Set<AuthFunction> authFunctions = new HashSet<AuthFunction>(0);

	public AuthRole() {
	}

	public AuthRole(String id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public AuthRole(String id, String name, String code, String information, Set<User> users,
			Set<AuthFunction> authFunctions) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.information = information;
		this.users = users;
		this.authFunctions = authFunctions;
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

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<AuthFunction> getAuthFunctions() {
		return this.authFunctions;
	}

	public void setAuthFunctions(Set<AuthFunction> authFunctions) {
		this.authFunctions = authFunctions;
	}

}