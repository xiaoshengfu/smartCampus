package com.sdkdjn.smartcampus.dao;

import java.util.List;

import com.sdkdjn.smartcampus.dao.base.IBaseDao;
import com.sdkdjn.smartcampus.entity.MajorClass;

public interface IMajorClassDao extends IBaseDao<MajorClass> {

	List<MajorClass> findAllMajorClassBySchool(String id);

}
