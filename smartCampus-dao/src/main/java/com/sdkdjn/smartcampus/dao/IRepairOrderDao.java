package com.sdkdjn.smartcampus.dao;

import java.sql.Timestamp;
import java.util.List;

import com.sdkdjn.smartcampus.dao.base.IBaseDao;
import com.sdkdjn.smartcampus.entity.RepairOrder;

public interface IRepairOrderDao extends IBaseDao<RepairOrder> {
	
	public int findCount(Integer orderState, Integer rating, Timestamp startTime, Timestamp endTime);

	public List<RepairOrder> findRepairOrdersByStudentId(String id);
}
