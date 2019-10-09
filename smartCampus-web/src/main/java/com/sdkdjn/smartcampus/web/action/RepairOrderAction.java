package com.sdkdjn.smartcampus.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sdkdjn.smartcampus.entity.RepairOrder;
import com.sdkdjn.smartcampus.entity.Student;
import com.sdkdjn.smartcampus.service.IRepairOrderService;
import com.sdkdjn.smartcampus.service.IStudentService;
import com.sdkdjn.smartcampus.utils.Highcharts;
import com.sdkdjn.smartcampus.utils.RepairOrderList;
import com.sdkdjn.smartcampus.utils.SmartCampusUtils;
import com.sdkdjn.smartcampus.utils.Transport;
import com.sdkdjn.smartcampus.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RepairOrderAction extends BaseAction<RepairOrder> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IRepairOrderService repairOrderService;
	@Autowired
	private IStudentService studentService;

	private File repairImage;
	private String repairImageFileType;
	private String equipment;

	public void setRepairImage(File repairImage) {
		this.repairImage = repairImage;
	}

	public void setRepairImageFileType(String repairImageFileType) {
		this.repairImageFileType = repairImageFileType;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	@RequiresPermissions("repairorder_list")
	public String pageQuery() {
		if (!"admin".equals(SmartCampusUtils.getLoginUser().getId())) {
			detachedCriteria.add(Restrictions.eq("school.id", SmartCampusUtils.getLoginUser().getSchool().getId()));
		}
		if (model.getOrderState() != null && model.getOrderState() != 4) {
			pageBean.setParameter("model.orderState=" + model.getOrderState());
			detachedCriteria.add(Restrictions.eq("orderState", model.getOrderState()));
		}
		dateRangeCriteria("orderDate");
		pageBean.setHref("repairOrderAction_pageQuery.action");
		repairOrderService.pageQuery(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return LIST;
	}

	@RequiresPermissions("repairorder_find")
	public String findRepairOrder() {
		RepairOrder repairOrder = repairOrderService.findRepairOrderById(model.getId());
		ServletActionContext.getRequest().setAttribute("repairOrder", repairOrder);
		return DETAILS;
	}

	@RequiresPermissions("repairorder_change")
	public String changeOrderState() {
		RepairOrder repairOrder = repairOrderService.changeOrderStateById(model.getId(), model.getOrderState());
		ServletActionContext.getRequest().setAttribute("repairOrder", repairOrder);
		return DETAILS;
	}

	@RequiresPermissions("repairorder_findpicture")
	public String findRepairOrderPicture() {
		if (StringUtils.isNotBlank(model.getPictureUrl())) {
			String realPath = ServletActionContext.getServletContext().getRealPath(model.getPictureUrl());
			File imgFile = new File(realPath);
			if (imgFile.exists()) {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("images/jpeg");
				response.setDateHeader("expries", -1);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Prama", "no-cache");
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				try {
					bis = new BufferedInputStream(new FileInputStream(imgFile));
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
		}
		return NONE;
	}

	public String analysisRepairOrders() {
		return ANALYSIS;
	}

	public String loadHighchartsData() {
		Highcharts highcharts = new Highcharts();
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
					.setTitle(startTime.toString().split(" ")[0] + " - " + endTime.toString().split(" ")[0] + "物业维修评价");
			repairOrderService.loadHighchartsData(highcharts, startTime, endTime);
			java2Json(highcharts, null);
		}
		return NONE;
	}

	public String terminalSubmitRepairOrder() {
		if ("android".equals(equipment)) {
			Transport<RepairOrder> transport = new Transport<RepairOrder>();
			Student student = studentService.findStudentById(model.getStudent().getId());
			model.setOrderState(0);
			model.setStudent(student);
			model.setSchool(student.getSchool());
			model.setOrderDate(new Timestamp(System.currentTimeMillis()));
			if (repairImage != null) {
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String stringDate = simpleDateFormat.format(date);
				String realpath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/repairimage") + "/"
						+ stringDate;
				File fileDir = new File(realpath);
				if (!fileDir.exists()) {
					fileDir.mkdir();
				}
				String fileName = new Timestamp(System.currentTimeMillis()).getTime() + model.getPlace() + "."
						+ repairImageFileType;
				File saveFile = new File(fileDir, fileName);
				model.setPictureUrl("/WEB-INF/repairimage/" + stringDate + "/" + fileName);
				try {
					FileUtils.copyFile(repairImage, saveFile);
					repairOrderService.saveRepairOrder(model);
				} catch (IOException e) {
					e.printStackTrace();
					transport.setState(0);
					java2Json(transport, new String[] {});
					return NONE;
				}
			} else {
				transport.setState(0);
				java2Json(transport, new String[] {});
				return NONE;
			}
		}
		return NONE;
	}

	public String terminalFindStudentRepairOrders() {
		if ("android".equals(equipment)) {
			RepairOrderList repairOrderList = new RepairOrderList();
			List<RepairOrder> repairOrders = repairOrderService.findRepairOrdersByStudentId(model.getStudent().getId());
			repairOrderList.setState(1);
			repairOrderList.setRepairOrders(repairOrders);
			java2Json(repairOrderList, new String[] { "school", "student" });
		}
		return NONE;
	}

	public String terminalFindRepairOrderPicture() {
		if ("android".equals(equipment)) {
			findRepairOrderPicture();
		}
		return NONE;
	}

	// 安卓端评论报修单
	public String terminalRepairOrderEvaluate() {
		Transport<RepairOrder> transport = new Transport<RepairOrder>();
		if ("android".equals(equipment) && StringUtils.isNotBlank(model.getId())) {
			RepairOrder repairOrder = repairOrderService.findRepairOrderById(model.getId());
			repairOrder.setRating(model.getRating());
			repairOrder.setEvaluationContent(model.getEvaluationContent());
			repairOrderService.saveRepairOrder(repairOrder);
			transport.setState(1);
			java2Json(transport, new String[] {});
		} else {
			transport.setState(0);
			java2Json(transport, new String[] {});
		}
		return NONE;
	}

}
