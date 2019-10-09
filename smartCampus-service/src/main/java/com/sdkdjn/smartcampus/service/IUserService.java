package com.sdkdjn.smartcampus.service;

import java.util.List;

import com.sdkdjn.smartcampus.entity.User;
import com.sdkdjn.smartcampus.utils.PageBean;

public interface IUserService {

	public void pageQuery(PageBean pageBean);

	public User findUserById(String id);

	public void updateUserById(User model, boolean resetPassword, boolean resetOpenid);

	public void addUser(User model, String[] userRoles);

	public void batchAddUser(List<User> users);

	public void updateUser(User user);
}
