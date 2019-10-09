package com.sdkdjn.smartcampus.entity;

import java.sql.Timestamp;


public class RepairOrder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private School school;
	private Student student;
	private Timestamp orderDate;
	private String pictureUrl;
	private String place;
	private String information;
	private Integer orderState;
	private Integer rating;
	private String evaluationContent;
	private String telephone;

	public RepairOrder() {
	}

	public RepairOrder(String id, School school, Student student, Timestamp orderDate, String place, String telephone) {
		this.id = id;
		this.school = school;
		this.student = student;
		this.orderDate = orderDate;
		this.place = place;
		this.telephone = telephone;
	}

	public RepairOrder(String id, School school, Student student, Timestamp orderDate, String pictureUrl, String place,
			String information, Integer orderState, Integer rating, String evaluationContent, String telephone) {
		this.id = id;
		this.school = school;
		this.student = student;
		this.orderDate = orderDate;
		this.pictureUrl = pictureUrl;
		this.place = place;
		this.information = information;
		this.orderState = orderState;
		this.rating = rating;
		this.evaluationContent = evaluationContent;
		this.telephone = telephone;
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

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getPictureUrl() {
		return this.pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Integer getOrderState() {
		return this.orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getEvaluationContent() {
		return this.evaluationContent;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}