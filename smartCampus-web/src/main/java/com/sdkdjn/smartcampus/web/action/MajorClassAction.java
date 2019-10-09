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
import com.sdkdjn.smartcampus.service.IMajorClassService;
import com.sdkdjn.smartcampus.service.ISchoolService;
import com.sdkdjn.smartcampus.utils.SmartCampusUtils;
import com.sdkdjn.smartcampus.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class MajorClassAction extends BaseAction<MajorClass> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IMajorClassService majorClassService;
	@Autowired
	private ISchoolService schoolService;

	private File majorClassFile;

	public void setMajorClassFile(File majorClassFile) {
		this.majorClassFile = majorClassFile;
	}

	@RequiresPermissions("majorclass_list")
	public String pageQuery() {
		if(!"admin".equals(SmartCampusUtils.getLoginUser().getId())){
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
		}
		pageBean.setHref("majorClassAction_pageQuery.action");
		majorClassService.pageQuery(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return LIST;
	}
	
	@RequiresPermissions("majorclass_find")
	public String findMajorClass() {
		if (model.getId() != null) {
			MajorClass majorClass = majorClassService.findMajorClassById(model.getId());
			ServletActionContext.getRequest().setAttribute("majorClass", majorClass);
		}
		return EDIT;
	}

	@RequiresPermissions("majorclass_update")
	public String updateMajorClass() {
		if (StringUtils.isNotBlank(model.getId())) {
			majorClassService.updateMajorClassById(model);
		}
		return pageQuery();
	}

	@RequiresPermissions("majorclass_addpage")
	public String addMajorClassPage() {
		return ADD;
	}

	@RequiresPermissions("majorclass_add")
	public String addMajorClass() {
		try {
			School school = schoolService.findSchoolById(SmartCampusUtils.getLoginUser().getSchool().getId());
			model.setSchool(school);
			majorClassService.addMajorClass(model);
		} catch (Exception e) {
			ServletActionContext.getRequest().setAttribute("message", 0);
			return ADD;
		}
		ServletActionContext.getRequest().setAttribute("message", 1);
		return ADD;
	}

	@RequiresPermissions("majorclass_batchadd")
	public String batchAddMajorClass() {
		try {
			List<MajorClass> majorClasses = new ArrayList<MajorClass>();
			School school = schoolService.findSchoolById(SmartCampusUtils.getLoginUser().getSchool().getId());
			// 使用POI解析Excel文件
			HSSFWorkbook hss = new HSSFWorkbook(new FileInputStream(majorClassFile));
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
				String id = row.getCell(0).getStringCellValue();
				String name = row.getCell(1).getStringCellValue();
				String information = row.getCell(2).getStringCellValue();
				// 包装一个学生对象
				MajorClass majorClass = new MajorClass(id, school, name, information);
				majorClasses.add(majorClass);
			}
			// 批量保存
			majorClassService.batchAddMajorClass(majorClasses);
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("batchMessage", 0);
			return BATCH_ADD;
		}
		ServletActionContext.getRequest().setAttribute("batchMessage", 1);
		return BATCH_ADD;
	}

	@RequiresPermissions("majorclass_batchaddpage")
	public String batchAddMajorClassPage() {
		return BATCH_ADD;
	}

	public String downloadMajorClassExcelDemo() {
		downloadExcelDemo("/WEB-INF/exceldemo/majorclass.xls", "批量导入班级信息样表.xls");
		return NONE;
	}
}
