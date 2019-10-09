package com.sdkdjn.smartcampus.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdkdjn.smartcampus.dao.ISigninDetailDao;
import com.sdkdjn.smartcampus.dao.base.impl.BaseDaoImpl;
import com.sdkdjn.smartcampus.entity.SigninDetail;

@Repository
@SuppressWarnings("unchecked")
public class SigninDetailDaoImpl extends BaseDaoImpl<SigninDetail> implements ISigninDetailDao {

	@Override
	public int findTotalSigninPeople(String id) {
		String hql = "SELECT COUNT(*) FROM SigninDetail signinDetail WHERE signinDetail.signinActivity.id=? AND signinDetail.signinstate=1";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, id);
		return list.size() > 0 ? list.get(0).intValue() : 0;
	}

	@Override
	public SigninDetail findSigninDetailByIds(String signinActivityId, String studentId, Integer signinstate) {
		String hql = "FROM SigninDetail WHERE signinActivity.id=? AND student.id=? AND signinstate= ?";
		List<SigninDetail> list = (List<SigninDetail>) this.getHibernateTemplate().find(hql, signinActivityId,
				studentId, signinstate);
		return list.size() != 0 ? list.get(0) : null;
	}
}
