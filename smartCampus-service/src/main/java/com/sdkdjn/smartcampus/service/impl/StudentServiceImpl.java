package com.sdkdjn.smartcampus.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkdjn.smartcampus.dao.IMajorClassDao;
import com.sdkdjn.smartcampus.dao.IStudentDao;
import com.sdkdjn.smartcampus.entity.MajorClass;
import com.sdkdjn.smartcampus.entity.Student;
import com.sdkdjn.smartcampus.service.IStudentService;
import com.sdkdjn.smartcampus.utils.PageBean;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentDao studentDao;
	@Autowired
	private IMajorClassDao majorClassDao;

	@Override
	public List<Student> findNoSigninStudent(String majorClassId, String signinActivityId) {
		return studentDao.findNoSigninStudent(majorClassId, signinActivityId);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		studentDao.pageQuery(pageBean);
	}

	@Override
	public Student findStudentById(String id) {
		return studentDao.findById(id);
	}

	@Override
	public void updateStudentById(Student model, boolean resetPassword, boolean resetOpenid) {
		Student student = studentDao.findById(model.getId());
		if (student != null) {
			if (StringUtils.isNotBlank(model.getName())) {
				if (!student.getName().equals(model.getName())) {
					student.setName(model.getName());
				}
			}
			if (StringUtils.isNotBlank(model.getMajorClass().getId())) {
				if (!student.getMajorClass().getId().equals(model.getMajorClass().getId())) {
					MajorClass majorClass = majorClassDao.findById(model.getMajorClass().getId());
					student.setMajorClass(majorClass);
				}
			}
			if (resetPassword) {
				student.setPassword("e10adc3949ba59abbe56e057f20f883e");
			}
			if (resetOpenid) {
				student.setOpenid(null);
			}
		}
	}

	@Override
	public void addStudent(Student model) {
		studentDao.save(model);
	}

	@Override
	public void batchAddStudent(List<Student> students) {
		for (Student student : students) {
			studentDao.saveOrUpdate(student);
		}
	}

	@Override
	public Student findStudentByOpenid(String openid) {
		return studentDao.findStudentByOpenid(openid);
	}

	@Override
	public void updateStudent(Student student) {
		studentDao.save(student);
	}
}
