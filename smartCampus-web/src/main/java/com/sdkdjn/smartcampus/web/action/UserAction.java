package com.sdkdjn.smartcampus.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sdkdjn.smartcampus.entity.AuthRole;
import com.sdkdjn.smartcampus.entity.Curriculum;
import com.sdkdjn.smartcampus.entity.School;
import com.sdkdjn.smartcampus.entity.User;
import com.sdkdjn.smartcampus.service.IAuthRoleService;
import com.sdkdjn.smartcampus.service.ICurriculumService;
import com.sdkdjn.smartcampus.service.ISchoolService;
import com.sdkdjn.smartcampus.service.IUserService;
import com.sdkdjn.smartcampus.utils.MD5Utils;
import com.sdkdjn.smartcampus.utils.SmartCampusUtils;
import com.sdkdjn.smartcampus.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserService userService;
	@Autowired
	private ISchoolService schoolService;
	@Autowired
	private IAuthRoleService authRoleService;
	@Autowired
	private ICurriculumService curriculumService;
	// 验证码
	private String checkcode;
	private boolean resetPassword;
	private boolean resetOpenid;
	private String[] userRoles;
	private File userFile;
	private File userCurriculumFile;
	private String userAuthRole;
	private String curriculumId;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}

	public void setResetOpenid(boolean resetOpenid) {
		this.resetOpenid = resetOpenid;
	}

	public void setUserRoles(String[] userRoles) {
		this.userRoles = userRoles;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}

	public void setUserCurriculumFile(File userCurriculumFile) {
		this.userCurriculumFile = userCurriculumFile;
	}

	public void setUserAuthRole(String userAuthRole) {
		this.userAuthRole = userAuthRole;
	}

	public void setCurriculumId(String curriculumId) {
		this.curriculumId = curriculumId;
	}

	public String login() {
		// 从Session中获取生成的验证码
		String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		// 校验验证码是否输入正确
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)) {
			// 使用shiro框架提供的方式进行认证操作
			Subject subject = SecurityUtils.getSubject();// 获得当前用户对象,状态为“未认证”
			AuthenticationToken token = new UsernamePasswordToken(model.getId(), MD5Utils.md5(model.getPassword()));// 创建用户名密码令牌对象
			try {
				subject.login(token);
			} catch (Exception e) {
				ServletActionContext.getRequest().setAttribute("error", "密码或账号输入错误!");
				return LOGIN;
			}
			User user = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			return HOME;
		} else {
			// 输入的验证码错误,设置提示信息，跳转到登录页面
			ServletActionContext.getRequest().setAttribute("error", "验证码输入错误!");
			return LOGIN;
		}
	}

	public String welcome() {
		return WELCOME;
	}

	@RequiresPermissions("user_list")
	public String pageQuery() {
		if (!"admin".equals(SmartCampusUtils.getLoginUser().getId())) {
			detachedCriteria.add(Restrictions.eq("school.id", SmartCampusUtils.getLoginUser().getSchool().getId()));
		}
		if (StringUtils.isNotBlank(model.getId())) {
			pageBean.setParameter("model.id=" + model.getId());
			detachedCriteria.add(Restrictions.eq("id", model.getId()));
		} else {
			if (StringUtils.isNotBlank(model.getName())) {
				pageBean.setParameter("model.name=" + model.getName());
				detachedCriteria.add(Restrictions.like("name", "%" + model.getName() + "%"));
			}
			if (StringUtils.isNotBlank(userAuthRole) && !"all".equals(userAuthRole)) {
				pageBean.setParameter("userAuthRole=" + userAuthRole);
				detachedCriteria.createAlias("authRoles", "a");
				detachedCriteria.add(Restrictions.eq("a.id", userAuthRole));
			}
		}
		pageBean.setHref("userAction_pageQuery.action");
		userService.pageQuery(pageBean);
		List<AuthRole> authRoles = authRoleService.findAllAuthRole();
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		ServletActionContext.getRequest().setAttribute("authRoles", authRoles);
		return LIST;
	}

	@RequiresPermissions("user_find")
	public String findUser() {
		if (model.getId() != null) {
			User user = userService.findUserById(model.getId());
			ServletActionContext.getRequest().setAttribute("user", user);
		}
		return EDIT;
	}

	@RequiresPermissions("user_update")
	public String updateUser() {
		if (StringUtils.isNotBlank(model.getId())) {
			userService.updateUserById(model, resetPassword, resetOpenid);
		}
		return pageQuery();
	}

	@RequiresPermissions("user_addpage")
	public String addUserPage() {
		if ("admin".equals(SmartCampusUtils.getLoginUser().getId())) {
			List<School> schools = schoolService.findAllSchool();
			ServletActionContext.getRequest().setAttribute("schools", schools);
		} else {
			List<AuthRole> authRoles = authRoleService.findAllAuthRole();
			ServletActionContext.getRequest().setAttribute("authRoles", authRoles);
		}
		return ADD;
	}

	@RequiresPermissions("user_add")
	public String addUser() {
		try {
			model.setPassword(MD5Utils.md5(model.getPassword()));
			model.setUserstate(1);
			userService.addUser(model, userRoles);
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("message", 0);
			return addUserPage();
		}
		ServletActionContext.getRequest().setAttribute("message", 1);
		return addUserPage();
	}

	@RequiresPermissions("user_batchaddpage")
	public String batchAddUserPage() {
		return BATCH_ADD;
	}

	@RequiresPermissions("user_batchadd")
	public String batchAddUser() {
		try {
			List<User> users = new ArrayList<User>();
			School school = schoolService.findSchoolById(SmartCampusUtils.getLoginUser().getSchool().getId());
			AuthRole teacherAuth = authRoleService.findAuthRoleById("2");
			AuthRole logisticsAuth = authRoleService.findAuthRoleById("3");
			// 使用POI解析Excel文件
			HSSFWorkbook hss = new HSSFWorkbook(new FileInputStream(userFile));
			// 根据名称获得指定Sheet对象
			HSSFSheet hssfSheet = hss.getSheet("Sheet1");
			for (Row row : hssfSheet) {
				int rowNum = row.getRowNum();
				if (rowNum == 0) {
					continue;
				}
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
				String id = row.getCell(0).getStringCellValue();
				String name = row.getCell(1).getStringCellValue();
				String telephone = row.getCell(2).getStringCellValue();
				String password = MD5Utils.md5(row.getCell(3).getStringCellValue());
				int userstate = (int) row.getCell(5).getNumericCellValue();
				// 包装一个学生对象
				User user = new User(id, school, name, telephone, password, null, userstate);
				switch ((int) row.getCell(4).getNumericCellValue()) {
				case 1:
					user.getAuthRoles().add(teacherAuth);
					break;
				case 2:
					user.getAuthRoles().add(logisticsAuth);
					break;
				case 3:
					user.getAuthRoles().add(teacherAuth);
					user.getAuthRoles().add(logisticsAuth);
					break;
				}
				users.add(user);
			}
			// 批量保存
			userService.batchAddUser(users);
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("batchMessage", 0);
			return BATCH_ADD;
		}
		ServletActionContext.getRequest().setAttribute("batchMessage", 1);
		return BATCH_ADD;
	}

	public String downloadUserExcelDemo() {
		downloadExcelDemo("/WEB-INF/exceldemo/user.xls", "批量导入用户信息样表.xls");
		return NONE;
	}

	public String addUserCurriculumPage() {
		User user = userService.findUserById(model.getId());
		List<Curriculum> curriculums = curriculumService.findAllCurriculumBySchoolId(SmartCampusUtils.getLoginUser().getSchool().getId());
		ServletActionContext.getRequest().setAttribute("curriculums", curriculums);
		ServletActionContext.getRequest().setAttribute("user", user);
		return ADD_USER_CURRICULUM;
	}

	public String addUserCurriculum() {
		try {
			User user = userService.findUserById(model.getId());
			Curriculum curriculum = curriculumService.findCurriculumById(curriculumId);
			user.getCurriculums().add(curriculum);
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("message", 0);
			return addUserCurriculumPage();
		}
		ServletActionContext.getRequest().setAttribute("message", 1);
		return addUserCurriculumPage();
	}

	public String batchAddUserCurriculumPage() {
		return BATCH_ADD_USER_CURRICULUM;
	}

	public String batchAddUserCurriculum() {
		try {
			Set<Curriculum> set;
			// 使用POI解析Excel文件
			HSSFWorkbook hss = new HSSFWorkbook(new FileInputStream(userCurriculumFile));
			// 根据名称获得指定Sheet对象
			HSSFSheet hssfSheet = hss.getSheet("Sheet1");
			for (Row row : hssfSheet) {
				int rowNum = row.getRowNum();
				if (rowNum == 0) {
					continue;
				}
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				User user = userService.findUserById(row.getCell(0).getStringCellValue());
				set = user.getCurriculums();
				set.removeAll(set);
				String[] curriculumIds = row.getCell(1).getStringCellValue().split(",");
				for (String curriculumId : curriculumIds) {
					Curriculum curriculum = curriculumService.findCurriculumById(curriculumId);
					set.add(curriculum);
				}
				userService.updateUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("batchMessage", 0);
			return BATCH_ADD_USER_CURRICULUM;
		}
		ServletActionContext.getRequest().setAttribute("batchMessage", 1);
		return BATCH_ADD_USER_CURRICULUM;
	}

	public String downloadUserCurriculumExcelDemo() {
		downloadExcelDemo("/WEB-INF/exceldemo/user_curriculum.xls", "批量导入教师课程样表.xls");
		return NONE;
	}

	public String teacherPageQuery() {
		if (StringUtils.isNotBlank(model.getId())) {
			pageBean.setParameter("model.id=" + model.getId());
			detachedCriteria.add(Restrictions.eq("id", model.getId()));
		} else {
			if (StringUtils.isNotBlank(model.getName())) {
				pageBean.setParameter("model.name=" + model.getName());
				detachedCriteria.add(Restrictions.like("name", "%" + model.getName() + "%"));
			}
		}
		pageBean.setParameter("userAuthRole=" + userAuthRole);
		detachedCriteria.createAlias("authRoles", "a");
		detachedCriteria.add(Restrictions.eq("a.id", "2"));
		pageBean.setHref("userAction_teacherPageQuery.action");
		userService.pageQuery(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return TEACHER_LIST;
	}

	public String userCurriculumPageQuery() {
		DetachedCriteria userDetachedCriteria = DetachedCriteria.forClass(Curriculum.class);
		pageBean.setDetachedCriteria(userDetachedCriteria);
		userDetachedCriteria.createAlias("users", "u");
		userDetachedCriteria.add(Restrictions.eq("u.id", model.getId()));
		pageBean.setParameter("model.id=" + model.getId());
		pageBean.setHref("userAction_userCurriculumPageQuery.action");
		curriculumService.pageQuery(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		ServletActionContext.getRequest().setAttribute("user", model);
		return TEACHER_CURRICULUM_LIST;
	}

	public String deleteUserCurriculum() {
		User user = userService.findUserById(model.getId());
		user.getCurriculums().remove(new Curriculum(curriculumId, null, null, null));
		userService.updateUser(user);
		return userCurriculumPageQuery();
	}
}
