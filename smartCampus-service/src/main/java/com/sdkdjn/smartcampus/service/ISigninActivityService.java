package com.sdkdjn.smartcampus.service;

import java.sql.Timestamp;
import java.util.List;

import com.sdkdjn.smartcampus.entity.SigninActivity;
import com.sdkdjn.smartcampus.utils.PageBean;
import com.sdkdjn.smartcampus.utils.SigninHighcharts;

public interface ISigninActivityService {

	public void pageQuery(PageBean pageBean);

	public SigninActivity findSigninActivityById(String id);

	public void deleteSigninActivityById(String id);

	public void launchSigninActivity(SigninActivity signinActivity);

	public List<SigninActivity> findSigninActivityByMajorClassId(String id, Integer signinActivityType);

	public void save(SigninActivity signinActivity);

	public List<SigninActivity> findAllMeetingSigninActivity();

	public void loadHighchartsData(SigninHighcharts highcharts, Timestamp startTime, Timestamp endTime);
}
