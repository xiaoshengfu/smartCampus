package com.sdkdjn.smartcampus.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkdjn.smartcampus.dao.IUserDao;
import com.sdkdjn.smartcampus.entity.User;
import com.sdkdjn.smartcampus.service.IWeXinService;
@Service
@Transactional
public class WeXinServiceImpl implements IWeXinService{
	@Autowired
	private IUserDao userDao;

	public boolean WeXinBinding(User user ,String openid) {	
		try {
			User u = userDao.findById(user.getId());
			if (u!=null && u.getId().equals(user.getId()) && u.getPassword().equals(user.getPassword())) {
				u.setOpenid(openid);
				userDao.update(u);
				return true;
			}else {
				return false;
			}	
		} catch (Exception e) {
			return false;
		}
	}

	public User getUserByOpenId(String openid) {
		List<User> userlist = userDao.findUserByOpenID(openid);
		if (userlist!=null && userlist.size()>0) {
			return userlist.get(0);
		}else {
			return null;
		}
	}

	
}
