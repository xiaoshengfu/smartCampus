package com.sdkdjn.smartcampus.utils;

import java.util.List;

import com.sdkdjn.smartcampus.entity.RepairOrder;

public class RepairOrderList {
	private Integer state;
	private List<RepairOrder> repairOrders;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<RepairOrder> getRepairOrders() {
		return repairOrders;
	}

	public void setRepairOrders(List<RepairOrder> repairOrders) {
		this.repairOrders = repairOrders;
	}

}
