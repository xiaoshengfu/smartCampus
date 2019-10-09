package com.sdkdjn.smartcampus.service;

import java.util.List;

import com.sdkdjn.smartcampus.entity.School;

public interface ISchoolService {

	public List<School> findAllSchool();

	public School findSchoolById(String id);

}
