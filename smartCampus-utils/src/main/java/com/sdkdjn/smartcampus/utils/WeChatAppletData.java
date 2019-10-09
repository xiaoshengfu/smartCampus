package com.sdkdjn.smartcampus.utils;

import java.util.ArrayList;
import java.util.List;

import com.sdkdjn.smartcampus.entity.SigninActivity;
import com.sdkdjn.smartcampus.entity.Student;

public class WeChatAppletData {

	private Integer state;
	private Student student;
	private List<SigninActivity> signinActivities = new ArrayList<SigninActivity>();

	public WeChatAppletData() {
	}

	public WeChatAppletData(Integer state, Student student, List<SigninActivity> signinActivities) {
		this.state = state;
		this.student = student;
		this.signinActivities = signinActivities;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<SigninActivity> getSigninActivities() {
		return signinActivities;
	}

	public void setSigninActivities(List<SigninActivity> signinActivities) {
		this.signinActivities = signinActivities;
	}

}
