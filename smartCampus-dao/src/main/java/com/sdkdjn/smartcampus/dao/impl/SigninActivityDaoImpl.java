package com.sdkdjn.smartcampus.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdkdjn.smartcampus.dao.ISigninActivityDao;
import com.sdkdjn.smartcampus.dao.base.impl.BaseDaoImpl;
import com.sdkdjn.smartcampus.entity.SigninActivity;

@Repository
@SuppressWarnings("unchecked")
public class SigninActivityDaoImpl extends BaseDaoImpl<SigninActivity> implements ISigninActivityDao {

	@Override
	public List<SigninActivity> findSigninActivityByMajorClassId(String id, Integer SigninActivityType) {
		String hql = "SELECT s FROM SigninActivity s LEFT OUTER JOIN s.majorClasses m WHERE s.activityType = ? AND m.id = ? AND s.state = 1 ORDER BY s.activityDate DESC";
		List<SigninActivity> list = (List<SigninActivity>) this.getHibernateTemplate().find(hql, SigninActivityType,
				id);
		return list;
	}

	@Override
	public List<SigninActivity> findAllMeetingSigninActivity() {
		String hql = "SELECT s FROM SigninActivity s WHERE s.activityType = 0 AND s.state = 1 ORDER BY s.activityDate DESC";
		List<SigninActivity> list = (List<SigninActivity>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<SigninActivity> findSigninActivitysByActivityDate(Timestamp startTime, Timestamp endTime) {
		String hql = "SELECT s FROM SigninActivity s WHERE s.activityType = 1 AND s.state = 1 AND s.activityDate BETWEEN ? AND ?";
		List<SigninActivity> list = (List<SigninActivity>) this.getHibernateTemplate().find(hql, startTime, endTime);
		return list;
	}

}