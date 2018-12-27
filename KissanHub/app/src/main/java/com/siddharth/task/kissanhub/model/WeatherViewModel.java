package com.siddharth.task.kissanhub.model;

import java.util.List;

/**
 * Created by Siddharth Vanakudari on 27/12/18.
 */
public class WeatherViewModel {
	private List<Country> countryList;

	public WeatherViewModel(List<Country> countryList) {
		this.countryList = countryList;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	@Override
	public String toString() {
		return "WeatherViewModel{" +
				"countryList=" + countryList +
				'}';
	}
}
