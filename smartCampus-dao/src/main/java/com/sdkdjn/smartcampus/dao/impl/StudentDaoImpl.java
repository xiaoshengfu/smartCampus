package com.sdkdjn.smartcampus.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdkdjn.smartcampus.dao.IStudentDao;
import com.sdkdjn.smartcampus.dao.base.impl.BaseDaoImpl;
import com.sdkdjn.smartcampus.entity.Student;

@Repository
@SuppressWarnings("unchecked")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements IStudentDao {

	@Override
	public List<Student> findNoSigninStudent(String majorClassId, String signinActivityId) {
		String hql = "FROM Student WHERE majorClass.id = ? AND id NOT IN(SELECT student.id FROM SigninDetail WHERE signinActivity.id = ?)";
		List<Student> list = (List<Student>) this.getHibernateTemplate().find(hql, majorClassId, signinActivityId);
		return list;
	}

	@Override
	public Student findStudentByOpenid(String openid) {
		String hql = "FROM Student WHERE openid = ?";
		List<Student> list = (List<Student>) this.getHibernateTemplate().find(hql, openid);
		return list.size() != 0 ? list.get(0) : null;
	}
}
