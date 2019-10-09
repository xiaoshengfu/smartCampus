package com.sdkdjn.smartcampus.entity;

import java.util.HashSet;
import java.util.Set;

public class School implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String information;
	private Set<MajorClass> majorClasses = new HashSet<MajorClass>(0);
	private Set<Student> students = new HashSet<Student>(0);
	private Set<Curriculum> curriculums = new HashSet<Curriculum>(0);
	private Set<RepairOrder> repairOrders = new HashSet<RepairOrder>(0);
	private Set<User> users = new HashSet<User>(0);

	public School() {
	}

	public School(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public School(String id, String name, String information, Set<MajorClass> majorClasses, Set<Student> students,
			Set<Curriculum> curriculums, Set<RepairOrder> repairOrders, Set<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.information = information;
		this.majorClasses = majorClasses;
		this.students = students;
		this.curriculums = curriculums;
		this.repairOrders = repairOrders;
		this.users = users;
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

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Set<MajorClass> getMajorClasses() {
		return majorClasses;
	}

	public void setMajorClasses(Set<MajorClass> majorClasses) {
		this.majorClasses = majorClasses;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Curriculum> getCurriculums() {
		return curriculums;
	}

	public void setCurriculums(Set<Curriculum> curriculums) {
		this.curriculums = curriculums;
	}

	public Set<RepairOrder> getRepairOrders() {
		return repairOrders;
	}

	public void setRepairOrders(Set<RepairOrder> repairOrders) {
		this.repairOrders = repairOrders;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}