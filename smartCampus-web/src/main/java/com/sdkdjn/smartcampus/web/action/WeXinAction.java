package com.sdkdjn.smartcampus.web.action;



import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
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
import com.sdkdjn.smartcampus.service.IWeXinService;
import com.sdkdjn.smartcampus.utils.MD5Utils;
import com.sdkdjn.smartcampus.utils.SmartCampusUtils;
import com.sdkdjn.smartcampus.wechat.util.WeixinUtil;

@Controller
@Scope("prototype")
public class WeXinAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Autowired
	private ISigninActivityService signinActivityService;
	@Autowired
	private IWeXinService weXinService;
	@Autowired
	private IMajorClassService majorClassService;
	@Autowired
	private ICurriculumService curriculumService;
	@Autowired
	private ISigninDetailService signinDetailService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IUserService userService;
	
	public String button23() throws ParseException, IOException{
		String code = SmartCampusUtils.getRequest().getParameter("code");
		String openid = WeixinUtil.getOpenId(code);
		String nikename = WeixinUtil.getUserInfo(WeixinUtil.getAccessToken().getToken(), openid).getNickname();
		HttpSession session = SmartCampusUtils.getSession();
		session.setAttribute("openid", nikename);
		User user = weXinService.getUserByOpenId(nikename);
		if (user!=null) {
			session.setAttribute("qduser", user);
			return "qiandao1";	
		}else {
			return "binding";
		}
	}
	
	public String button33() throws ParseException, IOException{
		String code = SmartCampusUtils.getRequest().getParameter("code");
		String openid = WeixinUtil.getOpenId(code);
		String nikename = WeixinUtil.getUserInfo(WeixinUtil.getAccessToken().getToken(), openid).getNickname();
		HttpSession session = SmartCampusUtils.getSession();
		session.setAttribute("openid", nikename);
		User user = weXinService.getUserByOpenId(nikename);
		if (user!=null) {
			session.setAttribute("userinfo", user);
			return "savesuccess";
		}else {
			return "binding";
		}
	}
	
	public String binding(){
		HttpServletRequest request = SmartCampusUtils.getRequest();
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(userid,username,MD5Utils.md5(password));
		String openid = (String) SmartCampusUtils.getSession().getAttribute("openid");
		if (weXinService.WeXinBinding(user, openid)) {
			return "success";
		}else {
			return "binding";
		}
	}

	public String createqrcode(){
		return null;
	}

	/**
	 * 签到选择
	 */
	private String qiandaostate;
	public void setQiandaostate(String qiandaostate) {
		this.qiandaostate = qiandaostate;
	}
	public String qiandaoxz(){
		if (qiandaostate.equals("1")) {
			return "qiandaoforsk";
		}else {
			return "qiandaoforhy";
		}
	}

	/**
	 * 上课签到
	 */
	private String qdfsk_kc;
	private String[] qdfsk_bj;
	private Integer qdfsk_time;
	private String qdfsk_ms;

	public void setQdfsk_kc(String qdfsk_kc) {
		this.qdfsk_kc = qdfsk_kc;
	}

	public void setQdfsk_bj(String[] qdfsk_bj) {
		this.qdfsk_bj = qdfsk_bj;
	}

	public void setQdfsk_time(Integer qdfsk_time) {
		this.qdfsk_time = qdfsk_time;
	}

	public void setQdfsk_ms(String qdfsk_ms) {
		this.qdfsk_ms = qdfsk_ms;
	}

	public String qdfSK(){
		try {	
			SigninActivity signinActivity = new SigninActivity();
			User user = (User) SmartCampusUtils.getSession().getAttribute("qduser");
			System.out.println(user.getName());
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			signinActivity.setId(uuid);
			signinActivity.setUser(user);
			signinActivity.setActivityDate(new Timestamp(System.currentTimeMillis()));
			signinActivity.setActivityType(1);
			signinActivity.setDuration(qdfsk_time);
			signinActivity.setInformation(qdfsk_ms);
			int total = 0;
			MajorClass majorClass = null;
			signinActivity.setCurriculum(curriculumService.findCurriculumById(qdfsk_kc));
			for (String MajorClassId : qdfsk_bj) {
				majorClass = majorClassService.findMajorClassById(MajorClassId);
				signinActivity.getMajorClasses().add(majorClass);
				total += majorClass.getStudents().size();
			}
			signinActivity.setTotalPeople(total);	
			signinActivityService.save(signinActivity);
			for (String MajorClassId : qdfsk_bj) {
				majorClass = majorClassService.findMajorClassById(MajorClassId);
				Set<Student> sss = majorClass.getStudents();
				for (Student student : sss) {
					System.out.println(student.getId());
					SigninDetail siDetail = new SigninDetail(signinActivityService.findSigninActivityById(uuid),student,0);
					signinDetailService.addSigninDetail(siDetail);
				}
			}
			SmartCampusUtils.getSession().setAttribute("signinactivitycode", uuid);
			return "qiandaosuc";
		} catch (Exception e) {
			e.printStackTrace();
			return "qiandaoforsk";
		}
	}
	private String qdfsk_total;
	
	public void setQdfsk_total(String qdfsk_total) {
		this.qdfsk_total = qdfsk_total;
	}
	public String qdfHY(){
		try {	
			SigninActivity signinActivity = new SigninActivity();
			User user = (User) SmartCampusUtils.getSession().getAttribute("qduser");
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			signinActivity.setId(uuid);
			signinActivity.setUser(user);
			signinActivity.setActivityDate(new Timestamp(System.currentTimeMillis()));
			signinActivity.setActivityType(0);
			signinActivity.setDuration(qdfsk_time);
			signinActivity.setInformation(qdfsk_ms);
			signinActivity.setTotalPeople(Integer.parseInt(qdfsk_total));	
			signinActivityService.save(signinActivity);
			SmartCampusUtils.getSession().setAttribute("signinactivitycode", uuid);
			return "qiandaosuc";
		} catch (Exception e) {
			e.printStackTrace();
			return "qiandaoforyh";
		}
	}
}
