package com.sdkdjn.smartcampus.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 封装分页属性
 */
public class PageBean {
	private int visiblePages = 7;// 可见页数
	private int currentPage = 1;// 当前页码
	private int pageSize = 8;// 每页显示的记录数
	private DetachedCriteria detachedCriteria;// 查询条件
	private int total;// 总记录数
	private String href;// 访问路径
	private String parameter = "";// 参数
	private List<Object> rows;// 当前页需要展示的数据集合

	public int getVisiblePages() {
		return visiblePages;
	}

	public void setVisiblePages(int visiblePages) {
		this.visiblePages = visiblePages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter += ("&"+parameter);			
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
}
