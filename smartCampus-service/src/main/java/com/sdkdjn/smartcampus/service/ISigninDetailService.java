package com.sdkdjn.smartcampus.service;

import com.sdkdjn.smartcampus.entity.SigninDetail;
import com.sdkdjn.smartcampus.utils.PageBean;

public interface ISigninDetailService {

	public void pageQuery(PageBean pageBean);

	public int findTotalSigninPeople(String id);

	public void addSigninDetail(SigninDetail signinDetail);

	public SigninDetail findSigninDetailByIds(String signinActivityId, String studentId, Integer signinstate);

	public SigninDetail findSigninDetailById(String id);

	public void updateSigninDetail(SigninDetail signinDetail);
}
