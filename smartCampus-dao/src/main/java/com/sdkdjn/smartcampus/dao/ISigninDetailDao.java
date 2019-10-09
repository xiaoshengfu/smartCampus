package com.sdkdjn.smartcampus.dao;

import com.sdkdjn.smartcampus.dao.base.IBaseDao;
import com.sdkdjn.smartcampus.entity.SigninDetail;

public interface ISigninDetailDao extends IBaseDao<SigninDetail> {

	public int findTotalSigninPeople(String id);

	public SigninDetail findSigninDetailByIds(String signinActivityId, String studentId, Integer signinstate);
}
