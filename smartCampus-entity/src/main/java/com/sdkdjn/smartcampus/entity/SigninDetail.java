package com.sdkdjn.smartcampus.entity;

import java.sql.Timestamp;

public class SigninDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private SigninActivity signinActivity;
	private Student student;
	private Timestamp signinDate;
	private Double longitude;
	private Double latitude;
	private Integer signinstate;

	public SigninDetail(SigninActivity signinActivity, Student student, Timestamp signinDate, Double longitude,
			Double latitude, Integer signinstate) {
		this.signinActivity = signinActivity;
		this.student = student;
		this.signinDate = signinDate;
		this.longitude = longitude;
		this.latitude = latitude;
		this.signinstate = signinstate;
	}

	public SigninDetail() {
	}

	public SigninDetail(String id, SigninActivity signinActivity, Student student) {
		this.id = id;
		this.signinActivity = signinActivity;
		this.student = student;
	}

	public SigninDetail(String id, SigninActivity signinActivity, Student student, Timestamp signinDate,
			Double longitude, Double latitude, Integer signinstate) {
		this.id = id;
		this.signinActivity = signinActivity;
		this.student = student;
		this.signinDate = signinDate;
		this.longitude = longitude;
		this.latitude = latitude;
		this.signinstate = signinstate;
	}

	public SigninDetail(SigninActivity signinActivity, Student student, Integer signinstate) {
		this.signinActivity = signinActivity;
		this.student = student;
		this.signinstate = signinstate;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SigninActivity getSigninActivity() {
		return this.signinActivity;
	}

	public void setSigninActivity(SigninActivity signinActivity) {
		this.signinActivity = signinActivity;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Timestamp getSigninDate() {
		return this.signinDate;
	}

	public void setSigninDate(Timestamp signinDate) {
		this.signinDate = signinDate;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getSigninstate() {
		return this.signinstate;
	}

	public void setSigninstate(Integer signinstate) {
		this.signinstate = signinstate;
	}

}