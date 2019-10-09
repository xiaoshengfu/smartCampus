package com.sdkdjn.smartcampus.web.action;

import java.sql.Timestamp;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sdkdjn.smartcampus.entity.MajorClass;
import com.sdkdjn.smartcampus.entity.SigninActivity;
import com.sdkdjn.smartcampus.entity.SigninDetail;
import com.sdkdjn.smartcampus.entity.Student;
import com.sdkdjn.smartcampus.service.ISigninActivityService;
import com.sdkdjn.smartcampus.service.ISigninDetailService;
import com.sdkdjn.smartcampus.service.IStudentService;
import com.sdkdjn.smartcampus.utils.Transport;
import com.sdkdjn.smartcampus.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SigninDetailAction extends BaseAction<SigninDetail> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ISigninDetailService signinDetailService;
	@Autowired
	private ISigninActivityService signinActivityService;
	@Autowired
	private IStudentService studentService;

	@RequiresPermissions("signindetail_list")
	public String findSigninPageQuery() {
		pageBean.getDetachedCriteria().add(Restrictions.eq("signinActivity.id", model.getSigninActivity().getId()));
		pageBean.getDetachedCriteria().add(Restrictions.eq("signinstate", 1));
		pageBean.setHref("signinDetailAction_findSigninPageQuery.action");
		pageBean.setParameter("model.signinActivity.id=" + model.getSigninActivity().getId());
		signinDetailService.pageQuery(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		ServletActionContext.getRequest().setAttribute("signinActivity",
				signinActivityService.findSigninActivityById(model.getSigninActivity().getId()));
		return LIST;
	}

	@RequiresPermissions("signindetail_listno")
	public String findNoSigninPageQuery() {
		pageBean.getDetachedCriteria().add(Restrictions.eq("signinActivity.id", model.getSigninActivity().getId()));
		pageBean.getDetachedCriteria().add(Restrictions.eq("signinstate", 0));
		pageBean.setHref("signinDetailAction_findNoSigninPageQuery.action");
		pageBean.setParameter("model.signinActivity.id=" + model.getSigninActivity().getId());
		signinDetailService.pageQuery(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		ServletActionContext.getRequest().setAttribute("signinActivityId", model.getSigninActivity().getId());
		return LIST_NO;
	}

	public String replenishment() {
		SigninDetail signinDetail = signinDetailService.findSigninDetailById(model.getId());
		signinDetail.setSigninstate(1);
		signinDetail.setSigninDate(new Timestamp(System.currentTimeMillis()));
		signinDetailService.updateSigninDetail(signinDetail);
		return findNoSigninPageQuery();
	}

	public String terminalSignin() {
		Transport<SigninDetail> transport = new Transport<SigninDetail>();
		SigninActivity signinActivity = signinActivityService.findSigninActivityById(model.getId());
		Student student = studentService.findStudentById(model.getStudent().getId());
		if (signinActivity != null) {
			long time = signinActivity.getActivityDate().getTime();
			Integer duration = signinActivity.getDuration();
			time += duration * 60L * 1000L;
			if (time > System.currentTimeMillis()) {
				if (signinActivity.getActivityType() == 1) {
					Set<MajorClass> majorClasses = signinActivity.getMajorClasses();
					for (MajorClass majorClass : majorClasses) {
						if (majorClass.getId().equals(student.getMajorClass().getId())) {
							SigninDetail signinDetail = signinDetailService.findSigninDetailByIds(signinActivity.getId(), student.getId(), 0);
							if (signinDetail != null) {
								signinDetail.setSigninstate(1);
								signinDetail.setSigninDate(new Timestamp(System.currentTimeMillis()));
								signinDetailService.updateSigninDetail(signinDetail);
								transport.setState(1);
								java2Json(transport, new String[] {});
							} else {
								transport.setState(3);
								java2Json(transport, new String[] {});
							}
							return NONE;
						}
					}
				} else {
					SigninDetail signinDetail = new SigninDetail();
					signinDetail.setStudent(student);
					signinDetail.setSigninActivity(signinActivity);
					signinDetail.setSigninDate(new Timestamp(System.currentTimeMillis()));
					signinDetail.setSigninstate(1);
					signinDetailService.addSigninDetail(signinDetail);
					transport.setState(1);
					java2Json(transport, new String[] {});
				}
			} else {
				transport.setState(2);
				java2Json(transport, new String[] {});
			}
		} else {
			transport.setState(0);
			java2Json(transport, new String[] {});
		}
		return NONE;
	}
}
