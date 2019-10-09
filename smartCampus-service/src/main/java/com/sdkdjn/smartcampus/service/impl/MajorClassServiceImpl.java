package com.sdkdjn.smartcampus.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkdjn.smartcampus.dao.IMajorClassDao;
import com.sdkdjn.smartcampus.entity.MajorClass;
import com.sdkdjn.smartcampus.service.IMajorClassService;
import com.sdkdjn.smartcampus.utils.PageBean;

@Service
@Transactional
public class MajorClassServiceImpl implements IMajorClassService {

	@Autowired
	private IMajorClassDao majorclassDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		majorclassDao.pageQuery(pageBean);
	}

	@Override
	public MajorClass findMajorClassById(String id) {
		return majorclassDao.findById(id);
	}

	@Override
	public void updateMajorClassById(MajorClass model) {
		MajorClass majorClass = majorclassDao.findById(model.getId());
		if (StringUtils.isNotBlank(model.getName())) {
			majorClass.setName(model.getName());
		}
		majorClass.setInformation(model.getInformation());
	}

	@Override
	public List<MajorClass> findAllMajorClassBySchoolId(String id) {
		return majorclassDao.findAllMajorClassBySchool(id);
	}

	@Override
	public void addMajorClass(MajorClass model) {
		majorclassDao.save(model);
	}

	@Override
	public MajorClass findSchoolById(String id) {
		return majorclassDao.findById(id);
	}

	@Override
	public void batchAddMajorClass(List<MajorClass> majorClasses) {
		for (MajorClass majorClass : majorClasses) {
			majorclassDao.saveOrUpdate(majorClass);
		}
	}
}
