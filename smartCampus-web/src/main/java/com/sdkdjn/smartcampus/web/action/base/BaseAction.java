package com.sdkdjn.smartcampus.web.action.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sdkdjn.smartcampus.utils.FileUtils;
import com.sdkdjn.smartcampus.utils.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 表现层通用实现
 * 
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = 1L;
	public static final String WELCOME = "welcome";
	public static final String HOME = "home";
	public static final String LIST = "list";
	public static final String LIST_NO = "list_no";
	public static final String DETAILS = "details";
	public static final String EDIT = "edit";
	public static final String ADD = "add";
	public static final String BATCH_ADD = "batch_add";
	public static final String ADD_USER_CURRICULUM = "add_user_curriculum";
	public static final String BATCH_ADD_USER_CURRICULUM = "batch_add_user_curriculum";
	public static final String TEACHER_LIST = "teacher_list";
	public static final String TEACHER_CURRICULUM_LIST = "teacher_curriculum_list";
	public static final String  ANALYSIS = "analysis";
	protected PageBean pageBean = new PageBean();
	// 创建离线提交查询对象
	protected DetachedCriteria detachedCriteria = null;
	// 模型对象
	protected T model;
	// 时间范围
	protected String reservation;

	// 在构造方法中动态获取实体类型，通过反射创建model对象
	@SuppressWarnings("unchecked")
	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获得BaseAction上声明的泛型数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		// 通过反射创建对象
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public T getModel() {
		return model;
	}

	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	/**
	 * 将指定Java对象转为json，并响应到客户端页面
	 * 
	 * @param o
	 * @param exclueds
	 */
	public void java2Json(Object o, String[] exclueds) {
		JsonConfig jsonConfig = new JsonConfig();
		// 指定哪些属性不需要转json
		jsonConfig.setExcludes(exclueds);
		String json = JSONObject.fromObject(o, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将指定Java对象转为json，并响应到客户端页面
	 * 
	 * @param o
	 * @param exclueds
	 */
	public void java2Json(List<Object> o, String[] exclueds) {
		JsonConfig jsonConfig = new JsonConfig();
		// 指定哪些属性不需要转json
		jsonConfig.setExcludes(exclueds);
		String json = JSONArray.fromObject(o, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dateRangeCriteria(String dateField) {
		if (reservation != null) {
			String[] date = reservation.split(" - ");
			Timestamp startTime = new Timestamp(System.currentTimeMillis());
			Timestamp endTime = new Timestamp(System.currentTimeMillis());

			date[0] += " 00:00:00"; // 开始的一天的开始时间字符串
			date[1] += " 23:59:59"; // 结束的一天的结束时间字符串
			try {
				startTime = Timestamp.valueOf(date[0]);
				endTime = Timestamp.valueOf(date[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageBean.setParameter("reservation=" + reservation);
			detachedCriteria.add(Restrictions.between(dateField, startTime, endTime));
		}
	}

	public void downloadExcelDemo(String path, String filename) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String realPath = ServletActionContext.getServletContext().getRealPath(path);
			File studentExcelDemoFile = new File(realPath);
			response.setContentType("application/vnd.ms-excel");
			// 获取客户端浏览器类型
			String agent = ServletActionContext.getRequest().getHeader("User-Agent");
			filename = FileUtils.encodeDownloadFilename(filename, agent);
			response.setHeader("content-disposition", "attachment;filename=" + filename);
			if (studentExcelDemoFile.exists()) {
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				try {
					bis = new BufferedInputStream(new FileInputStream(studentExcelDemoFile));
					bos = new BufferedOutputStream(response.getOutputStream());
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = bis.read(buffer)) != -1) {
						bos.write(buffer, 0, len);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (bos != null) {
						try {
							bos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
