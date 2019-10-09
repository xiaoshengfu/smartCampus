package com.sdkdjn.smartcampus.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkdjn.smartcampus.dao.ICurriculumDao;
import com.sdkdjn.smartcampus.entity.Curriculum;
import com.sdkdjn.smartcampus.service.ICurriculumService;
import com.sdkdjn.smartcampus.utils.PageBean;

@Service
@Transactional
public class CurriculumServiceImpl implements ICurriculumService {

	@Autowired
	private ICurriculumDao curriculumDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		curriculumDao.pageQuery(pageBean);
	}

	@Override
	public List<Curriculum> findAllCurriculum() {
		return curriculumDao.findAll();
	}

	@Override
	public Curriculum findCurriculumById(String id) {
		return curriculumDao.findById(id);
	}

	@Override
	public void updateCurriculumById(Curriculum model) {
		Curriculum curriculum = curriculumDao.findById(model.getId());
		if (!curriculum.getName().equals(model.getName())) {
			curriculum.setName(model.getName());
		}
		if (!StringUtils.isNotBlank(curriculum.getInformation())) {
			curriculum.setInformation(model.getInformation());
		} else {
			if (!curriculum.getInformation().equals(model.getInformation())) {
				curriculum.setInformation(model.getInformation());
			}
		}
	}

	@Override
	public void addCurriculum(Curriculum model) {
		curriculumDao.save(model);
	}

	@Override
	public void batchAddCurriculum(List<Curriculum> curriculums) {
		for (Curriculum curriculum : curriculums) {
			curriculumDao.saveOrUpdate(curriculum);
		}
	}

	@Override
	public List<Curriculum> findAllCurriculumBySchoolId(String id) {
		return curriculumDao.findAllCurriculumBySchoolId(id);
	}
}
