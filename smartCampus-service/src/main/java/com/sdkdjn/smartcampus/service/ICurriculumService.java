package com.sdkdjn.smartcampus.service;

import java.util.List;

import com.sdkdjn.smartcampus.entity.Curriculum;
import com.sdkdjn.smartcampus.utils.PageBean;

public interface ICurriculumService {

	public void pageQuery(PageBean pageBean);

	public List<Curriculum> findAllCurriculum();

	public Curriculum findCurriculumById(String id);

	public void updateCurriculumById(Curriculum model);

	public void addCurriculum(Curriculum model);

	public void batchAddCurriculum(List<Curriculum> curriculums);

	public List<Curriculum> findAllCurriculumBySchoolId(String id);
}
