package com.sdkdjn.smartcampus.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdkdjn.smartcampus.dao.ICurriculumDao;
import com.sdkdjn.smartcampus.dao.base.impl.BaseDaoImpl;
import com.sdkdjn.smartcampus.entity.Curriculum;

@Repository
@SuppressWarnings("unchecked")
public class CurriculumDaoImpl extends BaseDaoImpl<Curriculum> implements ICurriculumDao {

	@Override
	public List<Curriculum> findAllCurriculumBySchoolId(String id) {
		String hql = "FROM Curriculum c WHERE c.school.id = ?";
		List<Curriculum> list = (List<Curriculum>) this.getHibernateTemplate().find(hql, id);
		return list;
	}

}
