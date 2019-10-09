package com.sdkdjn.smartcampus.entity;

import java.util.HashSet;
import java.util.Set;

public class MajorClass implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private School school;
	private String name;
	private String information;
	private Set<Student> students = new HashSet<Student>(0);
	private Set<SigninActivity> signinActivities = new HashSet<SigninActivity>(0);

	public MajorClass() {
	}

	public MajorClass(String id, School school, String name, String information) {
		this.id = id;
		this.school = school;
		this.name = name;
		this.information = information;
	}

	public MajorClass(String id, School school, String name, String information, Set<Student> students,
			Set<SigninActivity> signinActivities) {
		this.id = id;
		this.school = school;
		this.name = name;
		this.information = information;
		this.students = students;
		this.signinActivities = signinActivities;
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

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<SigninActivity> getSigninActivities() {
		return this.signinActivities;
	}

	public void setSigninActivities(Set<SigninActivity> signinActivities) {
		this.signinActivities = signinActivities;
	}

}