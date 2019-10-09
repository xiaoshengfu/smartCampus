package com.sdkdjn.smartcampus.entity;

import java.util.HashSet;
import java.util.Set;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private School school;
	private String name;
	private String telephone;
	private String password;
	private String openid;
	private Integer userstate;
	private Set<AuthRole> authRoles = new HashSet<AuthRole>(0);
	private Set<SigninActivity> signinActivities = new HashSet<SigninActivity>(0);
	private Set<Curriculum> curriculums = new HashSet<Curriculum>(0);

	public User() {
	}
	public User(String id ,String name, String password){
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public User(String id, School school, String name, String telephone, String password, String openid,
			Integer userstate) {
		super();
		this.id = id;
		this.school = school;
		this.name = name;
		this.telephone = telephone;
		this.password = password;
		this.openid = openid;
		this.userstate = userstate;
	}

	public User(String id, School school, String name, String telephone, String password, String openid,
			Integer userstate, Set<AuthRole> authRoles, Set<SigninActivity> signinActivities,
			Set<Curriculum> curriculums) {
		super();
		this.id = id;
		this.school = school;
		this.name = name;
		this.telephone = telephone;
		this.password = password;
		this.openid = openid;
		this.userstate = userstate;
		this.authRoles = authRoles;
		this.signinActivities = signinActivities;
		this.curriculums = curriculums;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getUserstate() {
		return userstate;
	}

	public void setUserstate(Integer userstate) {
		this.userstate = userstate;
	}

	public Set<AuthRole> getAuthRoles() {
		return this.authRoles;
	}

	public void setAuthRoles(Set<AuthRole> authRoles) {
		this.authRoles = authRoles;
	}

	public Set<SigninActivity> getSigninActivities() {
		return this.signinActivities;
	}

	public void setSigninActivities(Set<SigninActivity> signinActivities) {
		this.signinActivities = signinActivities;
	}

	public Set<Curriculum> getCurriculums() {
		return curriculums;
	}

	public void setCurriculums(Set<Curriculum> curriculums) {
		this.curriculums = curriculums;
	}
}