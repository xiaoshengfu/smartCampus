package com.sdkdjn.smartcampus.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.sdkdjn.smartcampus.entity.User;

public class SmartCampusUtils {

	// 获取session对象
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	// 获取登录用户对象
	public static User getLoginUser() {
		return (User) getSession().getAttribute("loginUser");
	}
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

}
