package com.sdkdjn.smartcampus.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdkdjn.smartcampus.dao.IUserDao;
import com.sdkdjn.smartcampus.dao.base.impl.BaseDaoImpl;
import com.sdkdjn.smartcampus.entity.User;

@Repository
@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User findUserByIdAndPassword(String id, String password) {
		String hql = "FROM User u WHERE u.id = ? AND u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, id, password);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<User> findUserByOpenID(String openid) {
		String hql = "FROM User u WHERE u.openid = ?";
		List<User> ls = null;
		ls = (List<User>) this.getHibernateTemplate().find(hql, openid);
		return ls;
	}
}
