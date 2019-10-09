package com.sdkdjn.smartcampus.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdkdjn.smartcampus.dao.IMajorClassDao;
import com.sdkdjn.smartcampus.dao.base.impl.BaseDaoImpl;
import com.sdkdjn.smartcampus.entity.MajorClass;

@Repository
@SuppressWarnings("unchecked")
public class MajorClassDaoImpl extends BaseDaoImpl<MajorClass> implements IMajorClassDao {

	@Override
	public List<MajorClass> findAllMajorClassBySchool(String id) {
		String hql = "FROM MajorClass m WHERE m.school.id = ?";
		List<MajorClass> list = (List<MajorClass>) this.getHibernateTemplate().find(hql, id);
		return list;
	}
}
