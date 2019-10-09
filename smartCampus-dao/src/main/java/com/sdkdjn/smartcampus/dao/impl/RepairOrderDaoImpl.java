package com.sdkdjn.smartcampus.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdkdjn.smartcampus.dao.IRepairOrderDao;
import com.sdkdjn.smartcampus.dao.base.impl.BaseDaoImpl;
import com.sdkdjn.smartcampus.entity.RepairOrder;

@Repository
@SuppressWarnings("unchecked")
public class RepairOrderDaoImpl extends BaseDaoImpl<RepairOrder> implements IRepairOrderDao {

	@Override
	public int findCount(Integer orderState, Integer rating, Timestamp startTime, Timestamp endTime) {
		String hql1 = "SELECT COUNT(*) FROM RepairOrder r WHERE orderState=? AND r.rating = ? AND r.orderDate BETWEEN ? AND ?";
		String hql2 = "SELECT COUNT(*) FROM RepairOrder r WHERE orderState=? AND r.rating IS NULL AND r.orderDate BETWEEN ? AND ?";
		if (rating != null) {
			List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql1, orderState, rating, startTime, endTime);
			return list.size() > 0 ? list.get(0).intValue() : 0;
		} else {
			List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql2, orderState, startTime, endTime);
			return list.size() > 0 ? list.get(0).intValue() : 0;
		}
	}

	@Override
	public List<RepairOrder> findRepairOrdersByStudentId(String id) {
		String hql = "FROM RepairOrder r WHERE r.student.id=? ORDER BY r.orderDate DESC";
		List<RepairOrder> list = (List<RepairOrder>) this.getHibernateTemplate().find(hql, id);
		
		return list;
	}
}
