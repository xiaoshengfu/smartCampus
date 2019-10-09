package com.sdkdjn.smartcampus.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdkdjn.smartcampus.dao.IAuthFunctionDao;
import com.sdkdjn.smartcampus.dao.base.impl.BaseDaoImpl;
import com.sdkdjn.smartcampus.entity.AuthFunction;

@Repository
@SuppressWarnings("unchecked")
public class AuthFunctionDaoImpl extends BaseDaoImpl<AuthFunction> implements IAuthFunctionDao {

	@Override
	// 根据用户id查询对应的权限
	public List<AuthFunction> findAuthFunctionListByUserId(String id) {
		String hql = "SELECT DISTINCT f FROM AuthFunction f LEFT OUTER JOIN f.authRoles"
				+ " r LEFT OUTER JOIN r.users u WHERE u.id = ?";
		List<AuthFunction> list = (List<AuthFunction>) this.getHibernateTemplate().find(hql, id);
		return list;
	}

}
