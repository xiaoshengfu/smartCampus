package com.sdkdjn.smartcampus.utils;

import java.util.ArrayList;
import java.util.List;

public class Highcharts {

	private String title;
	private List<Series> series = new ArrayList<Series>();

	public Highcharts() {
	}

	public Highcharts(String title, List<Series> series) {
		this.title = title;
		this.series = series;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}
}
