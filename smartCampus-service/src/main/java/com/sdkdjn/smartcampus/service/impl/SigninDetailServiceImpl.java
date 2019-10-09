package com.sdkdjn.smartcampus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkdjn.smartcampus.dao.ISigninDetailDao;
import com.sdkdjn.smartcampus.entity.SigninDetail;
import com.sdkdjn.smartcampus.service.ISigninDetailService;
import com.sdkdjn.smartcampus.utils.PageBean;

@Service
@Transactional
public class SigninDetailServiceImpl implements ISigninDetailService {

	@Autowired
	private ISigninDetailDao signinDetailDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		signinDetailDao.pageQuery(pageBean);
	}

	@Override
	public int findTotalSigninPeople(String id) {
		return signinDetailDao.findTotalSigninPeople(id);
	}

	@Override
	public void addSigninDetail(SigninDetail signinDetail) {
		signinDetailDao.save(signinDetail);
	}

	@Override
	public SigninDetail findSigninDetailByIds(String signinActivityId, String studentId, Integer signinstate) {
		return signinDetailDao.findSigninDetailByIds(signinActivityId, studentId, signinstate);
	}

	@Override
	public SigninDetail findSigninDetailById(String id) {
		return signinDetailDao.findById(id);
	}

	@Override
	public void updateSigninDetail(SigninDetail signinDetail) {
		signinDetailDao.update(signinDetail);
	}
}
