package com.sdkdjn.smartcampus.web.action;

import java.sql.Timestamp;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sdkdjn.smartcampus.entity.MajorClass;
import com.sdkdjn.smartcampus.entity.SigninActivity;
import com.sdkdjn.smartcampus.entity.SigninDetail;
import com.sdkdjn.smartcampus.entity.Student;
import com.sdkdjn.smartcampus.entity.User;
import com.sdkdjn.smartcampus.service.ICurriculumService;
import com.sdkdjn.smartcampus.service.IMajorClassService;
import com.sdkdjn.smartcampus.service.ISigninActivityService;
import com.sdkdjn.smartcampus.service.ISigninDetailService;
import com.sdkdjn.smartcampus.service.IStudentService;
import com.sdkdjn.smartcampus.service.IUserService;
import com.sdkdjn.smartcampus.utils.SigninHighcharts;
import com.sdkdjn.smartcampus.utils.SmartCampusUtils;
import com.sdkdjn.smartcampus.utils.Transport;
import com.sdkdjn.smartcampus.utils.WeChatAppletData;
import com.sdkdjn.smartcampus.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SigninActivityAction extends BaseAction<SigninActivity> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ISigninActivityService signinActivityService;
	@Autowired
	private ISigninDetailService signinDetailService;
	@Autowired
	private IMajorClassService majorClassService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ICurriculumService curriculumService;
	@Autowired
	private IStudentService studentService;
	private String[] majorClassIds;
	private String equipment;
	private String openid;

	public void setMajorClassIds(String[] majorClassIds) {
		this.majorClassIds = majorClassIds;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@RequiresPermissions("signinactivity_list") // 执行这个方法，需要当前用户具有signinactivity_list这个权限
	public String pageQuery() {
		dateRangeCriteria("activityDate");
		if (!"admin".equals(SmartCampusUtils.getLoginUser().getId())) {
			Subject loginUser = SecurityUtils.getSubject();
			if (loginUser.hasRole("signinactivity_alllist")) {
				detachedCriteria.createAlias("user", "u");
				detachedCriteria
						.add(Restrictions.eq("u.school.id", SmartCampusUtils.getLoginUser().getSchool().getId()));
			} else {
				detachedCriteria.add(Restrictions.eq("user.id", SmartCampusUtils.getLoginUser().getId()));
			}
		}
		detachedCriteria.add(Restrictions.eq("state", 1));
		pageBean.setHref("signinActivityAction_pageQuery.action");
		signinActivityService.pageQuery(pageBean);
		List<Object> rows = pageBean.getRows();
		for (Object object : rows) {
			SigninActivity signinActivity = (SigninActivity) object;
			signinActivity.setTotalSigninPeople(signinDetailService.findTotalSigninPeople(signinActivity.getId()));
		}
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return LIST;
	}

	@RequiresPermissions("signinactivity_delete")
	public String deleteSigninActivity() {
		signinActivityService.deleteSigninActivityById(model.getId());
		return pageQuery();
	}

	public String analysisSigninActivity() {
		return ANALYSIS;
	}

	public String terminalLaunchSigninActivity() {
		if ("wechatofficialaccounts".equals(equipment)) {
			Transport<SigninActivity> transport = new Transport<SigninActivity>();
			User user = userService.findUserById(model.getUser().getId());
			model.setUser(user);
			model.setActivityDate(new Timestamp(System.currentTimeMillis()));
			int total = 0;
			MajorClass majorClass = null;
			if (model.getActivityType() == 1) {
				model.setCurriculum(curriculumService.findCurriculumById(model.getCurriculum().getId()));
				for (String MajorClassId : majorClassIds) {
					majorClass = majorClassService.findMajorClassById(MajorClassId);
					model.getMajorClasses().add(majorClass);
					total += majorClass.getStudents().size();
				}
				model.setTotalPeople(total);
			}
			try {
				signinActivityService.launchSigninActivity(model);
				if (model.getActivityType() == 0) {
					SigninActivity signinActivity = signinActivityService.findSigninActivityById(model.getId());
					for (String MajorClassId : majorClassIds) {
						majorClass = majorClassService.findMajorClassById(MajorClassId);
						for (Student student : majorClass.getStudents()) {
							SigninDetail signinDetail = new SigninDetail(signinActivity, student, 0);
							signinDetailService.addSigninDetail(signinDetail);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				transport.setState(0);
				java2Json(transport, new String[] {});
			}
			transport.setState(1);
			java2Json(transport, new String[] {});
		}
		return NONE;
	}

	public String terminalFindSigninActivityByOpenid() {
		if ("wechatapplet".equals(equipment)) {
			WeChatAppletData weChatAppletData = new WeChatAppletData();
			Student student = studentService.findStudentByOpenid(openid);
			if (student != null) {
				weChatAppletData.setState(1);
				weChatAppletData.setStudent(student);
				List<SigninActivity> signinActivities = signinActivityService.findAllMeetingSigninActivity();
				SigninActivity signinActivity = null;
				for (int i = 0, length = signinActivities.size(); i < length; i++) {
					signinActivity = signinActivities.get(i);
					if (signinActivity.getActivityDate().getTime()
							+ signinActivity.getDuration() * 60L * 1000L <= System.currentTimeMillis()) {
						signinActivities.remove(i);
						length--;
						continue;
					}
					signinActivity.setUserName(signinActivity.getUser().getName());
					if (signinDetailService.findSigninDetailByIds(signinActivity.getId(), model.getId(), 0) != null) {
						signinActivity.setSignin(true);
					} else {
						signinActivity.setSignin(false);
					}
				}
				weChatAppletData.setSigninActivities(signinActivities);
				java2Json(weChatAppletData, new String[] { "majorClass", "school", "repairOrders", "signinDetails",
						"curriculum", "user", "majorClasses", "signinDetails" });
			} else {
				weChatAppletData.setState(0);
				java2Json(weChatAppletData, new String[] {});
			}
		}
		return NONE;
	}

	public String loadHighchartsData() {
		SigninHighcharts highcharts = new SigninHighcharts();
		Timestamp startTime = new Timestamp(System.currentTimeMillis());
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		if (reservation != null) {
			String[] date = reservation.split(" - ");
			date[0] += " 00:00:00"; // 开始的一天的开始时间字符串
			date[1] += " 23:59:59"; // 结束的一天的结束时间字符串
			try {
				startTime = Timestamp.valueOf(date[0]);
				endTime = Timestamp.valueOf(date[1]);
			} catch (Exception e) {
				e.printStackTrace();
				return NONE;
			}
			highcharts
					.setTitle(startTime.toString().split(" ")[0] + " - " + endTime.toString().split(" ")[0] + "签到率折线图");
			
			signinActivityService.loadHighchartsData(highcharts, startTime, endTime);
			java2Json(highcharts, null);
		}
		return NONE;
	}
}
