package com.sdkdjn.smartcampus.dao;

import java.util.List;

import com.sdkdjn.smartcampus.dao.base.IBaseDao;
import com.sdkdjn.smartcampus.entity.AuthFunction;

public interface IAuthFunctionDao extends IBaseDao<AuthFunction> {
	public List<AuthFunction> findAuthFunctionListByUserId(String id);
}
