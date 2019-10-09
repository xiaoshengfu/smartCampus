package com.sdkdjn.smartcampus.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sdkdjn.smartcampus.entity.MajorClass;
import com.sdkdjn.smartcampus.entity.School;
import com.sdkdjn.smartcampus.entity.SigninActivity;
import com.sdkdjn.smartcampus.entity.Student;
import com.sdkdjn.smartcampus.service.IMajorClassService;
import com.sdkdjn.smartcampus.service.ISchoolService;
import com.sdkdjn.smartcampus.service.ISigninActivityService;
import com.sdkdjn.smartcampus.service.ISigninDetailService;
import com.sdkdjn.smartcampus.service.IStudentService;
import com.sdkdjn.smartcampus.utils.MD5Utils;
import com.sdkdjn.smartcampus.utils.SmartCampusUtils;
import com.sdkdjn.smartcampus.utils.Transport;
import com.sdkdjn.smartcampus.utils.WeChatAppletData;
import com.sdkdjn.smartcampus.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IMajorClassService majorClassService;
	@Autowired
	private ISchoolService schoolService;
	@Autowired
	private ISigninActivityService signinActivityService;
	@Autowired
	private ISigninDetailService signinDetailService;
	private boolean resetPassword;
	private boolean resetOpenid;
	private File studentFile;
	private String equipment;
	private String newPassword;

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}

	public void setResetOpenid(boolean resetOpenid) {
		this.resetOpenid = resetOpenid;
	}

	public void setStudentFile(File studentFile) {
		this.studentFile = studentFile;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@RequiresPermissions("student_list")
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
			if (model.getMajorClass() != null) {
				if (StringUtils.isNotBlank(model.getMajorClass().getName())) {
					pageBean.setParameter("model.majorClass.name=" + model.getMajorClass().getName());
					detachedCriteria.createAlias("majorClass", "m");
					detachedCriteria.add(Restrictions.like("m.name", "%" + model.getMajorClass().getName() + "%"));
				}
			}
		}
		pageBean.setHref("studentAction_pageQuery.action");
		studentService.pageQuery(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return LIST;
	}

	public String editStudentPage() {
		if (model.getId() != null) {
			Student student = studentService.findStudentById(model.getId());
			ServletActionContext.getRequest().setAttribute("student", student);
		}
		List<MajorClass> majorClasses = majorClassService
				.findAllMajorClassBySchoolId(SmartCampusUtils.getLoginUser().getSchool().getId());
		ServletActionContext.getRequest().setAttribute("majorClasses", majorClasses);
		return EDIT;
	}

	@RequiresPermissions("student_update")
	public String updateStudent() {
		if (StringUtils.isNotBlank(model.getId())) {
			studentService.updateStudentById(model, resetPassword, resetOpenid);
		}
		return pageQuery();
	}

	@RequiresPermissions("student_batchaddpage")
	public String batchAddStudentPage() {
		return BATCH_ADD;
	}

	@RequiresPermissions("student_addpage")
	public String addStudentPage() {
		List<MajorClass> majorClasses = majorClassService
				.findAllMajorClassBySchoolId(SmartCampusUtils.getLoginUser().getSchool().getId());
		ServletActionContext.getRequest().setAttribute("majorClasses", majorClasses);
		return ADD;
	}

	@RequiresPermissions("student_add")
	public String addStudent() {
		try {
			School school = schoolService.findSchoolById(SmartCampusUtils.getLoginUser().getSchool().getId());
			MajorClass majorClass = majorClassService.findSchoolById(model.getMajorClass().getId());
			model.setSchool(school);
			model.setMajorClass(majorClass);
			model.setPassword(MD5Utils.md5(model.getPassword()));
			studentService.addStudent(model);
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("message", 0);
			return addStudentPage();
		}
		ServletActionContext.getRequest().setAttribute("message", 1);
		return addStudentPage();
	}

	@RequiresPermissions("student_batchadd")
	public String batchAddStudent() {
		try {
			List<Student> students = new ArrayList<Student>();
			School school = schoolService.findSchoolById(SmartCampusUtils.getLoginUser().getSchool().getId());
			// 使用POI解析Excel文件
			HSSFWorkbook hss = new HSSFWorkbook(new FileInputStream(studentFile));
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
				row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				String id = row.getCell(0).getStringCellValue();
				MajorClass majorClass = majorClassService.findMajorClassById(row.getCell(1).getStringCellValue());
				String name = row.getCell(2).getStringCellValue();
				String telephone = row.getCell(3).getStringCellValue();
				String password = MD5Utils.md5(row.getCell(4).getStringCellValue());
				// 包装一个学生对象
				Student student = new Student(id, majorClass, school, name, password, null, telephone);
				students.add(student);
			}
			// 批量保存
			studentService.batchAddStudent(students);
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("batchMessage", 0);
			return BATCH_ADD;
		}
		ServletActionContext.getRequest().setAttribute("batchMessage", 1);
		return BATCH_ADD;
	}

	public String downloadStudentExcelDemo() {
		downloadExcelDemo("/WEB-INF/exceldemo/student.xls", "批量导入学生信息样表.xls");
		return NONE;
	}

	public String terminalLogin() {
		if ("android".equals(equipment)) {
			Transport<Student> transport = new Transport<Student>();
			Student student = studentService.findStudentById(model.getId());
			if (student != null && student.getPassword().equals(MD5Utils.md5(model.getPassword()))) {
				transport.setState(1);
				student.setMajorClassName(student.getMajorClass().getName());
				student.setSchoolName(student.getSchool().getName());
				transport.setData(student);
				java2Json(transport, new String[] { "majorClass", "school", "repairOrders", "signinDetails" });

			} else {
				transport.setState(0);
				java2Json(transport, new String[] {});
			}
		}
		if ("wechatapplet".equals(equipment)) {
			WeChatAppletData weChatAppletData = new WeChatAppletData();
			Student student = studentService.findStudentByOpenid(model.getOpenid());
			if (student != null) {
				weChatAppletData.setState(1);
				weChatAppletData.setStudent(student);
				List<SigninActivity> signinActivities = signinActivityService
						.findSigninActivityByMajorClassId(student.getMajorClass().getId(), 1);
				SigninActivity signinActivity = null;
				List<SigninActivity> list = new ArrayList<SigninActivity>();
				for (int i = 0, length = signinActivities.size(); i < length; i++) {
					signinActivity = signinActivities.get(i);
					if (signinActivity.getActivityDate().getTime()
							+ signinActivity.getDuration() * 60L * 1000L <= System.currentTimeMillis()) {
						continue;
					}
					signinActivity.setUserName(signinActivity.getUser().getName());
					signinActivity.setCurriculumName(signinActivity.getCurriculum().getName());
					for (MajorClass majorClass : signinActivity.getMajorClasses()) {
						signinActivity.getMajorClassNames().add(majorClass.getName());
					}
					if (signinDetailService.findSigninDetailByIds(signinActivity.getId(), student.getId(), 1) != null) {
						signinActivity.setSignin(true);
					} else {
						signinActivity.setSignin(false);
					}
					list.add(signinActivity);
				}
				weChatAppletData.setSigninActivities(list);
				java2Json(weChatAppletData, new String[] { "majorClass", "school", "repairOrders", "signinDetails",
						"curriculum", "user", "majorClasses", "signinDetails" });
			} else {
				weChatAppletData.setState(0);
				java2Json(weChatAppletData, new String[] {});
			}
		}
		return NONE;
	}

	public String terminalBindOpenid() {
		Transport<Student> transport = new Transport<Student>();
		if ("wechatapplet".equals(equipment)) {
			Student student = studentService.findStudentById(model.getId());
			if (student != null && student.getOpenid() == null
					&& student.getPassword().equals(MD5Utils.md5(model.getPassword()))) {
				student.setOpenid(model.getOpenid());
				studentService.updateStudent(student);
				transport.setState(1);
				transport.setData(student);
				java2Json(transport, new String[] { "majorClass", "school", "repairOrders", "signinDetails" });
			} else {
				transport.setState(0);
				java2Json(transport, new String[] {});
			}
		}
		return NONE;
	}

	public String terminalFindStudent() {
		if ("android".equals(equipment)) {
			Transport<Student> transport = new Transport<Student>();
			Student student = studentService.findStudentById(model.getId());
			if (student != null) {
				transport.setState(1);
				transport.setData(student);
				java2Json(transport, new String[] {});
			} else {
				transport.setState(0);
				java2Json(transport, new String[] {});
			}
		}
		return NONE;
	}

	public String terminalEditStudent() {
		if ("android".equals(equipment)) {
			Transport<Student> transport = new Transport<Student>();
			Student student = studentService.findStudentById(model.getId());
			if (student != null && student.getPassword().equals(MD5Utils.md5(model.getPassword()))) {
				student.setPassword(MD5Utils.md5(newPassword));
				studentService.updateStudent(student);
				transport.setState(1);
				java2Json(transport, new String[] {});
			} else {
				transport.setState(0);
				java2Json(transport, new String[] {});
			}
		}
		return NONE;
	}
}