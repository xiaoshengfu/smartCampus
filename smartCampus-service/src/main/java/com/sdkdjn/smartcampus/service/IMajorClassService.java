package com.sdkdjn.smartcampus.service;

import java.util.List;

import com.sdkdjn.smartcampus.entity.MajorClass;
import com.sdkdjn.smartcampus.utils.PageBean;

public interface IMajorClassService {

	public void pageQuery(PageBean pageBean);

	public MajorClass findMajorClassById(String id);

	public void updateMajorClassById(MajorClass model);

	public List<MajorClass> findAllMajorClassBySchoolId(String id);

	public void addMajorClass(MajorClass model);

	public MajorClass findSchoolById(String id);

	public void batchAddMajorClass(List<MajorClass> majorClasses);
}
