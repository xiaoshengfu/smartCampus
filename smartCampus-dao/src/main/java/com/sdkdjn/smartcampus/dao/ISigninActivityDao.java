package com.sdkdjn.smartcampus.dao;

import java.sql.Timestamp;
import java.util.List;

import com.sdkdjn.smartcampus.dao.base.IBaseDao;
import com.sdkdjn.smartcampus.entity.SigninActivity;

public interface ISigninActivityDao extends IBaseDao<SigninActivity> {

	public List<SigninActivity> findSigninActivityByMajorClassId(String id, Integer signinActivityType);

	public List<SigninActivity> findAllMeetingSigninActivity();

	public List<SigninActivity> findSigninActivitysByActivityDate(Timestamp startTime, Timestamp endTime);
}
