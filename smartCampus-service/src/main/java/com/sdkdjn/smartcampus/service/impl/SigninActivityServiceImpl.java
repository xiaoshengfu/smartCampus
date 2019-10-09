package com.sdkdjn.smartcampus.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkdjn.smartcampus.dao.ISigninActivityDao;
import com.sdkdjn.smartcampus.entity.SigninActivity;
import com.sdkdjn.smartcampus.service.ISigninActivityService;
import com.sdkdjn.smartcampus.utils.PageBean;
import com.sdkdjn.smartcampus.utils.SigninHighcharts;

@Service
@Transactional
public class SigninActivityServiceImpl implements ISigninActivityService {

	@Autowired
	private ISigninActivityDao signinActivityDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		signinActivityDao.pageQuery(pageBean);
	}

	@Override
	public SigninActivity findSigninActivityById(String id) {
		return signinActivityDao.findById(id);
	}

	@Override
	public void deleteSigninActivityById(String id) {
		SigninActivity signinActivity = signinActivityDao.findById(id);
		signinActivity.setState(0);
	}

	@Override
	public void launchSigninActivity(SigninActivity signinActivity) {
		signinActivityDao.save(signinActivity);
	}

	@Override
	public List<SigninActivity> findSigninActivityByMajorClassId(String id, Integer signinActivityType) {
		return signinActivityDao.findSigninActivityByMajorClassId(id, signinActivityType);
	}

	@Override
	public void save(SigninActivity signinActivity) {
		signinActivityDao.save(signinActivity);
	}

	@Override
	public List<SigninActivity> findAllMeetingSigninActivity() {
		return signinActivityDao.findAllMeetingSigninActivity();
	}

	@Override
	public void loadHighchartsData(SigninHighcharts highcharts, Timestamp startTime, Timestamp endTime) {
		List<SigninActivity> signinActivities = signinActivityDao.findSigninActivitysByActivityDate(startTime,endTime);
		int total = signinActivities.size();
		for (int i = 0; i < total; i++) {
			SigninActivity signinActivity = signinActivities.get(i);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
