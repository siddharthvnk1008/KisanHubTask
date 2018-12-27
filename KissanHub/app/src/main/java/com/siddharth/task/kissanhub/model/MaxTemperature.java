package com.siddharth.task.kissanhub.model;

import com.siddharth.task.kissanhub.network.WeatherResult;

/**
 * Created by Siddharth Vanakudari on 27/12/18.
 */
public class MaxTemperature extends WeatherResult{

	@Override
	public String toString()
	{
		return "ClassPojo [month = "+month+", value = "+value+", year = "+year+"]";
	}
}
