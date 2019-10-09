package com.sdkdjn.smartcampus.service;

import java.util.List;

import com.sdkdjn.smartcampus.entity.Student;
import com.sdkdjn.smartcampus.utils.PageBean;

public interface IStudentService {

	public List<Student> findNoSigninStudent(String majorClassId, String signinActivityId);

	public void pageQuery(PageBean pageBean);

	public Student findStudentById(String id);

	public void updateStudentById(Student model, boolean resetPassword, boolean resetOpenid);

	public void addStudent(Student model);

	public void batchAddStudent(List<Student> students);

	public Student findStudentByOpenid(String openid);

	public void updateStudent(Student student);
}
