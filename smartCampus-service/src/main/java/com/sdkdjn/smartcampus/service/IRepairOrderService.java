package com.sdkdjn.smartcampus.service;

import java.sql.Timestamp;
import java.util.List;

import com.sdkdjn.smartcampus.entity.RepairOrder;
import com.sdkdjn.smartcampus.utils.Highcharts;
import com.sdkdjn.smartcampus.utils.PageBean;

public interface IRepairOrderService {

	public void pageQuery(PageBean pageBean);

	public RepairOrder findRepairOrderById(String id);

	public RepairOrder changeOrderStateById(String id, Integer orderState);

	public void loadHighchartsData(Highcharts highcharts, Timestamp startTime, Timestamp endTime);

	public void saveRepairOrder(RepairOrder repairOrder);

	public List<RepairOrder> findRepairOrdersByStudentId(String id);
}
