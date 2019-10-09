package com.sdkdjn.smartcampus.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkdjn.smartcampus.dao.IRepairOrderDao;
import com.sdkdjn.smartcampus.entity.RepairOrder;
import com.sdkdjn.smartcampus.service.IRepairOrderService;
import com.sdkdjn.smartcampus.utils.Highcharts;
import com.sdkdjn.smartcampus.utils.PageBean;
import com.sdkdjn.smartcampus.utils.Series;

@Service
@Transactional
public class RepairOrderServiceImpl implements IRepairOrderService {

	@Autowired
	private IRepairOrderDao repairOrderDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		repairOrderDao.pageQuery(pageBean);
	}

	@Override
	public RepairOrder findRepairOrderById(String id) {
		return repairOrderDao.findById(id);
	}

	@Override
	public RepairOrder changeOrderStateById(String id, Integer orderState) {
		RepairOrder repairOrder = repairOrderDao.findById(id);
		repairOrder.setOrderState(orderState);
		return repairOrder;
	}

	@Override
	public void loadHighchartsData(Highcharts highcharts, Timestamp startTime, Timestamp endTime) {
		int ratingnull = repairOrderDao.findCount(2, null, startTime, endTime);
		int rating1 = repairOrderDao.findCount(2, 1, startTime, endTime);
		int rating2 = repairOrderDao.findCount(2, 2, startTime, endTime);
		int rating3 = repairOrderDao.findCount(2, 3, startTime, endTime);
		int rating4 = repairOrderDao.findCount(2, 4, startTime, endTime);
		int rating5 = repairOrderDao.findCount(2, 5, startTime, endTime);
		double sum = ratingnull + rating1 + rating2 + rating3 + rating4 + rating5;
		if (sum != 0) {
			highcharts.getSeries().add(new Series("未评星", ratingnull / sum * 100, false, false));
			highcharts.getSeries().add(new Series("一星", rating1 / sum * 100, false, false));
			highcharts.getSeries().add(new Series("二星", rating2 / sum * 100, false, false));
			highcharts.getSeries().add(new Series("三星", rating3 / sum * 100, false, false));
			highcharts.getSeries().add(new Series("四星", rating4 / sum * 100, false, false));
			highcharts.getSeries().add(new Series("五星", rating5 / sum * 100, true, true));
		} else {
			highcharts.getSeries().add(new Series("无已处理的报修单", 100, false, false));
		}
	}

	@Override
	public void saveRepairOrder(RepairOrder repairOrder) {
		repairOrderDao.save(repairOrder);
	}

	@Override
	public List<RepairOrder> findRepairOrdersByStudentId(String id) {
		return repairOrderDao.findRepairOrdersByStudentId(id);
	}
}
