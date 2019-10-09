package com.sdkdjn.smartcampus.service;

import com.sdkdjn.smartcampus.entity.User;

public interface IWeXinService {

	public User getUserByOpenId(String openid);
	
	public boolean WeXinBinding(User user, String openid);
	
}
