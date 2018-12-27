package com.siddharth.task.kissanhub.domain;

import com.siddharth.task.kissanhub.model.WeatherViewModel; /**
 * Created by Siddharth Vanakudari on 27/12/18.
 */
public interface WeatherActivityContract {

	interface View{

		void showWeatherData(WeatherViewModel weatherViewModel);
	}

	interface Presenter{
		void init(WeatherActivityContract.View view);

		void loadWeatherData();
	}
}
