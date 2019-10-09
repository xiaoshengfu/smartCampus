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

import com.sdkdjn.smartcampus.entity.Curriculum;
import com.sdkdjn.smartcampus.entity.School;
import com.sdkdjn.smartcampus.service.ICurriculumService;
import com.sdkdjn.smartcampus.service.ISchoolService;
import com.sdkdjn.smartcampus.utils.SmartCampusUtils;
import com.sdkdjn.smartcampus.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class CurriculumAction extends BaseAction<Curriculum> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICurriculumService curriculumService;
	@Autowired
	private ISchoolService schoolService;
	private File curriculumFile;

	public void setCurriculumFile(File curriculumFile) {
		this.curriculumFile = curriculumFile;
	}	

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
		}
		pageBean.setHref("curriculumAction_pageQuery.action");
		curriculumService.pageQuery(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return LIST;
	}

	public String editCurriculumPage() {
		Curriculum curriculum = curriculumService.findCurriculumById(model.getId());
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);
		return EDIT;
	}

	public String updateCurriculum() {
		curriculumService.updateCurriculumById(model);
		return pageQuery();
	}

	public String addCurriculumPage() {
		return ADD;
	}

	public String addCurriculum() {
		try {
			School school = schoolService.findSchoolById(SmartCampusUtils.getLoginUser().getSchool().getId());
			model.setSchool(school);
			curriculumService.addCurriculum(model);
		} catch (Exception e) {
			ServletActionContext.getRequest().setAttribute("message", 0);
			return ADD;
		}
		ServletActionContext.getRequest().setAttribute("message", 1);
		return ADD;
	}

	@RequiresPermissions("majorclass_batchadd")
	public String batchAddCurriculum() {
		try {
			List<Curriculum> curriculums = new ArrayList<Curriculum>();
			School school = schoolService.findSchoolById(SmartCampusUtils.getLoginUser().getSchool().getId());
			// 使用POI解析Excel文件
			HSSFWorkbook hss = new HSSFWorkbook(new FileInputStream(curriculumFile));
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
				Curriculum curriculum = new Curriculum(id, school, name, information);
				curriculums.add(curriculum);
			}
			// 批量保存
			curriculumService.batchAddCurriculum(curriculums);
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("batchMessage", 0);
			return BATCH_ADD;
		}
		ServletActionContext.getRequest().setAttribute("batchMessage", 1);
		return BATCH_ADD;
	}

	@RequiresPermissions("majorclass_batchaddpage")
	public String batchAddCurriculumPage() {
		return BATCH_ADD;
	}

	public String downloadCurriculumExcelDemo() {
		downloadExcelDemo("/WEB-INF/exceldemo/curriculum.xls", "批量导入课程信息样表.xls");
		return NONE;
	}
}
