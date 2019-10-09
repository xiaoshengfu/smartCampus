package com.sdkdjn.smartcampus.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.sdkdjn.smartcampus.dao.IAuthFunctionDao;
import com.sdkdjn.smartcampus.dao.IUserDao;
import com.sdkdjn.smartcampus.entity.AuthFunction;
import com.sdkdjn.smartcampus.entity.User;

public class SmartCampusRealm extends AuthorizingRealm {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IAuthFunctionDao authFunctionDao;

	// 认证方法
	@SuppressWarnings("null")
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
		// 获得页面输入的用户名
		String id = passwordToken.getUsername();
		// 根据用户名查询数据库中的密码
		User user = userDao.findById(id);
		if (user == null && user.getUserstate() != 1) {
			// 页面输入的用户名不存在
			return null;
		}
		// 简单认证信息对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		// 框架负责比对数据库中的密码和页面输入的密码是否一致
		return info;
	}

	// 授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获取当前登录用户对象
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		// 根据当前登录用户查询数据库，获取实际对应的权限
		List<AuthFunction> list = null;
		if (user.getId().equals("admin")) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AuthFunction.class);
			// 超级管理员内置用户，查询所有权限数据
			list = authFunctionDao.findByCriteria(detachedCriteria);
		} else {
			list = authFunctionDao.findAuthFunctionListByUserId(user.getId());
		}

		for (AuthFunction authFunction : list) {
			info.addStringPermission(authFunction.getCode());
		}
		return info;
	}
}
