package com.sdkdjn.smartcampus.dao;

import java.util.List;

import com.sdkdjn.smartcampus.dao.base.IBaseDao;
import com.sdkdjn.smartcampus.entity.Student;

public interface IStudentDao extends IBaseDao<Student> {

	public List<Student> findNoSigninStudent(String majorClassId, String signinActivityId);

	public Student findStudentByOpenid(String openid);
}
