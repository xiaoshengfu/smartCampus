package com.sdkdjn.smartcampus.entity;

import java.util.HashSet;
import java.util.Set;

public class Student implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private MajorClass majorClass;
	private School school;
	private String name;
	private String password;
	private String openid;
	private String telephone;
	private String majorClassName;
	private String schoolName;
	private Set<RepairOrder> repairOrders = new HashSet<RepairOrder>(0);
	private Set<SigninDetail> signinDetails = new HashSet<SigninDetail>(0);

	public Student() {
	}

	public Student(String id, MajorClass majorClass, School school, String name, String password, String openid,
			String telephone) {
		this.id = id;
		this.majorClass = majorClass;
		this.school = school;
		this.name = name;
		this.password = password;
		this.openid = openid;
		this.telephone = telephone;
	}

	public Student(String id, MajorClass majorClass, School school, String name, String password, String openid,
			String telephone, Set<RepairOrder> repairOrders, Set<SigninDetail> signinDetails) {
		this.id = id;
		this.majorClass = majorClass;
		this.school = school;
		this.name = name;
		this.password = password;
		this.openid = openid;
		this.telephone = telephone;
		this.repairOrders = repairOrders;
		this.signinDetails = signinDetails;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MajorClass getMajorClass() {
		return this.majorClass;
	}

	public void setMajorClass(MajorClass majorClass) {
		this.majorClass = majorClass;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMajorClassName() {
		return majorClassName;
	}

	public void setMajorClassName(String majorClassName) {
		this.majorClassName = majorClassName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Set<RepairOrder> getRepairOrders() {
		return repairOrders;
	}

	public void setRepairOrders(Set<RepairOrder> repairOrders) {
		this.repairOrders = repairOrders;
	}

	public Set<SigninDetail> getSigninDetails() {
		return signinDetails;
	}

	public void setSigninDetails(Set<SigninDetail> signinDetails) {
		this.signinDetails = signinDetails;
	}

}