package com.sdkdjn.smartcampus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkdjn.smartcampus.dao.ISchoolDao;
import com.sdkdjn.smartcampus.entity.School;
import com.sdkdjn.smartcampus.service.ISchoolService;

@Service
@Transactional
public class SchoolServiceImpl implements ISchoolService {
	
	@Autowired
	private ISchoolDao schoolDao;

	@Override
	public List<School> findAllSchool() {
		return schoolDao.findAll();
	}

	@Override
	public School findSchoolById(String id) {
		return schoolDao.findById(id);
	}
}
