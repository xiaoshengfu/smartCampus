package com.sdkdjn.smartcampus.dao;

import java.util.List;

import com.sdkdjn.smartcampus.dao.base.IBaseDao;
import com.sdkdjn.smartcampus.entity.User;

public interface IUserDao extends IBaseDao<User> {
	
	public User findUserByIdAndPassword(String id, String password);

	public List<User> findUserByOpenID(String openid);
}
