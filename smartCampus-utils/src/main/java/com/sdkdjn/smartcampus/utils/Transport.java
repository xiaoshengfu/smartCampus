package com.sdkdjn.smartcampus.utils;

public class Transport<T> {
	//状态码
	private Integer state;
	//数据
	private T data;

	public Transport() {
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
