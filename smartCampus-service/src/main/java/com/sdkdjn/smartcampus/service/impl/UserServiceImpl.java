package com.sdkdjn.smartcampus.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkdjn.smartcampus.dao.IAuthRoleDao;
import com.sdkdjn.smartcampus.dao.ISchoolDao;
import com.sdkdjn.smartcampus.dao.IUserDao;
import com.sdkdjn.smartcampus.entity.AuthRole;
import com.sdkdjn.smartcampus.entity.School;
import com.sdkdjn.smartcampus.entity.User;
import com.sdkdjn.smartcampus.service.IUserService;
import com.sdkdjn.smartcampus.utils.MD5Utils;
import com.sdkdjn.smartcampus.utils.PageBean;
import com.sdkdjn.smartcampus.utils.SmartCampusUtils;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ISchoolDao schoolDao;
	@Autowired
	private IAuthRoleDao authRoleDao;

	public User login(User user) {
		// 使用MD5加密密码
		String password = MD5Utils.md5(user.getPassword());
		return userDao.findUserByIdAndPassword(user.getId(), password);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}

	@Override
	public User findUserById(String id) {
		return userDao.findById(id);
	}

	@Override
	public void updateUserById(User model, boolean resetPassword, boolean resetOpenid) {
		User user = userDao.findById(model.getId());
		if (user != null) {
			if (StringUtils.isNotBlank(model.getName())) {
				if (!user.getName().equals(model.getName())) {
					user.setName(model.getName());
				}
			}
			if (model.getUserstate() != null) {
				user.setTelephone(model.getTelephone());
			}
			if (resetPassword) {
				user.setPassword("e10adc3949ba59abbe56e057f20f883e");
			}
			if (resetOpenid) {
				user.setOpenid(null);
			}
			if (!user.getUserstate().equals(model.getUserstate()) && model.getUserstate() != null) {
				user.setUserstate(model.getUserstate());
			}
		}
	}

	@Override
	public void addUser(User model, String[] userRoles) {
		if ("admin".equals(SmartCampusUtils.getLoginUser().getId())) {
			School school = schoolDao.findById(model.getSchool().getId());
			model.getAuthRoles().add(authRoleDao.findById("1"));
			model.setSchool(school);
			userDao.save(model);
		} else {
			School school = schoolDao.findById(model.getSchool().getId());
			Set<AuthRole> authRoles = model.getAuthRoles();
			for (String authRoleId : userRoles) {
				authRoles.add(authRoleDao.findById(authRoleId));
			}
			model.setSchool(school);
			userDao.save(model);
		}
	}

	@Override
	public void batchAddUser(List<User> users) {
		for (User user : users) {
			userDao.saveOrUpdate(user);
		}
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}
}
