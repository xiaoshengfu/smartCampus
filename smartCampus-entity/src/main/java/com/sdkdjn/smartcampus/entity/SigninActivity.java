package com.sdkdjn.smartcampus.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SigninActivity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private User user;
	private Curriculum curriculum;
	private Integer activityType;
	private Integer duration;
	private String information;
	private Timestamp activityDate;
	private Integer totalPeople;
	private Double longitude;
	private Double latitude;
	private Integer state = 1;
	private Integer totalSigninPeople;
	private boolean signin;
	private String userName;
	private String curriculumName;
	private List<String> majorClassNames = new ArrayList<String>();
	private Set<MajorClass> majorClasses = new HashSet<MajorClass>(0);
	private Set<SigninDetail> signinDetails = new HashSet<SigninDetail>(0);

	public SigninActivity() {
	}

	public SigninActivity(String id, User user, Curriculum curriculum, Integer activityType, Integer duration,
			String information, Timestamp activityDate, Integer totalPeople, Double longitude, Double latitude,
			Integer state) {
		this.id = id;
		this.user = user;
		this.curriculum = curriculum;
		this.activityType = activityType;
		this.duration = duration;
		this.information = information;
		this.activityDate = activityDate;
		this.totalPeople = totalPeople;
		this.longitude = longitude;
		this.latitude = latitude;
		this.state = state;
	}

	public SigninActivity(String id, User user, Curriculum curriculum, Integer activityType, Integer duration,
			String information, Timestamp activityDate, Integer totalPeople, Double longitude, Double latitude,
			Integer state, Set<MajorClass> majorClasses, Set<SigninDetail> signinDetails) {
		this.id = id;
		this.user = user;
		this.curriculum = curriculum;
		this.activityType = activityType;
		this.duration = duration;
		this.information = information;
		this.activityDate = activityDate;
		this.totalPeople = totalPeople;
		this.longitude = longitude;
		this.latitude = latitude;
		this.state = state;
		this.majorClasses = majorClasses;
		this.signinDetails = signinDetails;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Timestamp getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Timestamp activityDate) {
		this.activityDate = activityDate;
	}

	public Integer getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(Integer totalPeople) {
		this.totalPeople = totalPeople;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set<MajorClass> getMajorClasses() {
		return majorClasses;
	}

	public Integer getTotalSigninPeople() {
		return totalSigninPeople;
	}

	public void setTotalSigninPeople(Integer totalSigninPeople) {
		this.totalSigninPeople = totalSigninPeople;
	}

	public boolean isSignin() {
		return signin;
	}

	public void setSignin(boolean signin) {
		this.signin = signin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurriculumName() {
		return curriculumName;
	}

	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}

	public List<String> getMajorClassNames() {
		return majorClassNames;
	}

	public void setMajorClassNames(List<String> majorClassNames) {
		this.majorClassNames = majorClassNames;
	}

	public void setMajorClasses(Set<MajorClass> majorClasses) {
		this.majorClasses = majorClasses;
	}

	public Set<SigninDetail> getSigninDetails() {
		return signinDetails;
	}

	public void setSigninDetails(Set<SigninDetail> signinDetails) {
		this.signinDetails = signinDetails;
	}
}