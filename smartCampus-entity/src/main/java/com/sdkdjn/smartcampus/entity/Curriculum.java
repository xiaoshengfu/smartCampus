package com.sdkdjn.smartcampus.entity;

import java.util.HashSet;
import java.util.Set;

public class Curriculum implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private School school;
	private String name;
	private String information;
	private Set<User> users = new HashSet<User>(0);
	private Set<SigninActivity> signinActivities = new HashSet<SigninActivity>(0);

	public Curriculum() {
	}

	public Curriculum(String id, School school, String name, String information) {
		super();
		this.id = id;
		this.school = school;
		this.name = name;
		this.information = information;
	}

	public Curriculum(String id, School school, String name, String information, Set<User> users,
			Set<SigninActivity> signinActivities) {
		this.id = id;
		this.school = school;
		this.name = name;
		this.information = information;
		this.users = users;
		this.signinActivities = signinActivities;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public School getSchool() {
		return school;
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

	public Set<SigninActivity> getSigninActivities() {
		return signinActivities;
	}

	public void setSigninActivities(Set<SigninActivity> signinActivities) {
		this.signinActivities = signinActivities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curriculum other = (Curriculum) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}