package com.sdkdjn.smartcampus.dao;

import java.util.List;

import com.sdkdjn.smartcampus.dao.base.IBaseDao;
import com.sdkdjn.smartcampus.entity.Curriculum;

public interface ICurriculumDao extends IBaseDao<Curriculum> {

	public List<Curriculum> findAllCurriculumBySchoolId(String id);

}
