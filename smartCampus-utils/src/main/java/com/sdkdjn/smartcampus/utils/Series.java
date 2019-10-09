package com.sdkdjn.smartcampus.utils;

public class Series {

	private String name;
	private double y;
	private boolean sliced;
	private boolean selected;

	public Series() {
	}

	public Series(String name, double y, boolean sliced, boolean selected) {
		this.name = name;
		this.y = y;
		this.sliced = sliced;
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean isSliced() {
		return sliced;
	}

	public void setSliced(boolean sliced) {
		this.sliced = sliced;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
