package com.sdkdjn.smartcampus.utils;

import java.util.List;

public class SigninHighcharts {

	private String title;
	private List<String> categories;
	private List<SigninSeries> series;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<SigninSeries> getSeries() {
		return series;
	}

	public void setSeries(List<SigninSeries> series) {
		this.series = series;
	}
}
