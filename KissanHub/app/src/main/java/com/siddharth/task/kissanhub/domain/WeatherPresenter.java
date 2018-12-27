package com.siddharth.task.kissanhub.domain;

import android.util.Log;

import com.siddharth.task.kissanhub.model.Country;
import com.siddharth.task.kissanhub.model.MaxTemperature;
import com.siddharth.task.kissanhub.model.MinimumTemperature;
import com.siddharth.task.kissanhub.model.RainFall;
import com.siddharth.task.kissanhub.model.Weather;
import com.siddharth.task.kissanhub.model.WeatherViewModel;
import com.siddharth.task.kissanhub.network.APIClient;
import com.siddharth.task.kissanhub.network.APIInterface;
import com.siddharth.task.kissanhub.network.URLConstants;
import com.siddharth.task.kissanhub.network.WeatherResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Siddharth Vanakudari on 27/12/18.
 */
public class WeatherPresenter implements WeatherActivityContract.Presenter{

	private static final String TAG = WeatherPresenter.class.getName();
	private WeatherActivityContract.View view;
	private Map<String,Country> countryMap;
	private WeatherViewModel weatherViewModel;
	private APIInterface apiInterface;

	private List<String> countryList =new ArrayList<>();

	private Map<String,String> minimumTempMap =new LinkedHashMap<>();
	private Map<String,String> maximumTempMap =new LinkedHashMap<>();
	private Map<String,String> rainfallMap =new LinkedHashMap<>();

	@Override
	public void init(WeatherActivityContract.View view) {
		this.view = view;
		countryMap = new LinkedHashMap<>();
		apiInterface = APIClient.getClient().create(APIInterface.class);

		countryList.add("England");
		countryList.add("Scotland");
		countryList.add("UK");
		countryList.add("Wales");

		minimumTempMap.put("England",URLConstants.England_MINIMUM_TEMP);
		minimumTempMap.put("Scotland",URLConstants.Scotland_MINIMUM_TEMP);
		minimumTempMap.put("UK",URLConstants.UK_MINIMUM_TEMP);
		minimumTempMap.put("Wales",URLConstants.Wales_MINIMUM_TEMP);

		maximumTempMap.put("England",URLConstants.England_MAXIMUM_TEMP);
		maximumTempMap.put("Scotland",URLConstants.Scotland_MAXIMUM_TEMP);
		maximumTempMap.put("UK",URLConstants.UK_MAXIMUM_TEMP);
		maximumTempMap.put("Wales",URLConstants.Wales_MAXIMUM_TEMP);

		rainfallMap.put("England",URLConstants.England_RAINFALL_TEMP);
		rainfallMap.put("Scotland",URLConstants.Scotland_RAINFALL_TEMP);
		rainfallMap.put("UK",URLConstants.UK_RAINFALL_TEMP);
		rainfallMap.put("Wales",URLConstants.Wales_RAINFALL_TEMP);
	}

	@Override
	public void loadWeatherData() {
		doRecurisveApicall();
	}

	private void doRecurisveApicall() {

		if(countryMap.size() < 4){
			for (String country: countryList){
				if(!countryMap.containsKey(country)){
					callMaxTempAPI(country);
				}
			}
		}
	}

	private void callMaxTempAPI(final String country) {
		Call<List<WeatherResult>> call = apiInterface.doGetMinTemparature(maximumTempMap.get(country));
		call.enqueue(new Callback<List<WeatherResult>>() {
			@Override
			public void onResponse(Call<List<WeatherResult>> call, Response<List<WeatherResult>> response) {
				List<WeatherResult> temperatures = response.body();
				List<MaxTemperature> maxTemperatures  = getListOfMaxTemp(temperatures);
				Log.d(TAG,"max temp : "+response.body()+"");
				callMinTempAPI(country,maxTemperatures);
			}

			@Override
			public void onFailure(Call<List<WeatherResult>> call, Throwable t) {
				call.cancel();
			}
		});
	}

	private void callMinTempAPI(final String country, final List<MaxTemperature> maxTemperatures) {
		Call<List<WeatherResult>> call = apiInterface.doGetMinTemparature(minimumTempMap.get(country));
		call.enqueue(new Callback<List<WeatherResult>>() {
			@Override
			public void onResponse(Call<List<WeatherResult>> call, Response<List<WeatherResult>> response) {
				List<WeatherResult> temperatures = response.body();
				List<MinimumTemperature> minTemperatures  = getListOfMinTemp(temperatures);
				Log.d(TAG,"min temp : "+response.body()+"");

				callRainFallTempAPI(country,maxTemperatures,minTemperatures);
			}

			@Override
			public void onFailure(Call<List<WeatherResult>> call, Throwable t) {
				call.cancel();
			}
		});
	}

	private void callRainFallTempAPI(final String country, final List<MaxTemperature> maxTemperatures, final List<MinimumTemperature> minTemperatures) {
		Call<List<WeatherResult>> call = apiInterface.doGetMinTemparature(rainfallMap.get(country));
		call.enqueue(new Callback<List<WeatherResult>>() {
			@Override
			public void onResponse(Call<List<WeatherResult>> call, Response<List<WeatherResult>> response) {
				List<WeatherResult> temperatures = response.body();
				List<RainFall> rainFalls = getListOfRainFallTemp(temperatures);
				Log.d(TAG, "Rainfall temp : " + response.body() + "");
				List<Weather> weatherList =new ArrayList<>();

				int smallest = (maxTemperatures.size() < minTemperatures.size()) ? ((maxTemperatures.size() < rainFalls.size()) ? maxTemperatures.size() : rainFalls.size()) : ((minTemperatures.size() < rainFalls.size()) ? minTemperatures.size() : rainFalls.size());

				Log.d(TAG, "Max temp size : " + maxTemperatures.size() + "");
				Log.d(TAG, "Min temp size : " + minTemperatures.size() + "");
				Log.d(TAG, "Rainfall size : " + rainFalls.size() + "");
				Log.d(TAG, "smallest : " + smallest + "");

				for (int i = 0; i < smallest; i++) {
					Weather weather =new Weather(minTemperatures.get(i),
							maxTemperatures.get(i),
							rainFalls.get(i));
					weatherList.add(weather);
				}
				countryMap.put(country, new Country(country, weatherList));
				if (countryMap.size() == 4) {
					List<Country> countries = new ArrayList<>();
					for(Map.Entry entry:countryMap.entrySet()){
						System.out.print(entry.getKey() + " : " + entry.getValue());
						countries.add((Country) entry.getValue());
					}
					weatherViewModel = new WeatherViewModel(countries);
					view.showWeatherData(weatherViewModel);
				}
			}

			@Override
			public void onFailure(Call<List<WeatherResult>> call, Throwable t) {
				call.cancel();
			}
		});
	}

	private List<MaxTemperature> getListOfMaxTemp(List<WeatherResult> temperatures) {
		List<MaxTemperature> maxTemperatures = new ArrayList<>();
		for (WeatherResult weatherResult : temperatures){
			MaxTemperature maxTemperature =new MaxTemperature();
			maxTemperature.setMonth(weatherResult.getMonth());
			maxTemperature.setYear(weatherResult.getYear());
			maxTemperature.setValue(weatherResult.getValue());
			maxTemperatures.add(maxTemperature);
		}
		return maxTemperatures;
	}

	private List<MinimumTemperature> getListOfMinTemp(List<WeatherResult> temperatures) {
		List<MinimumTemperature> maxTemperatures = new ArrayList<>();
		for (WeatherResult weatherResult : temperatures){
			MinimumTemperature minimumTemperature =new MinimumTemperature();
			minimumTemperature.setMonth(weatherResult.getMonth());
			minimumTemperature.setYear(weatherResult.getYear());
			minimumTemperature.setValue(weatherResult.getValue());
			maxTemperatures.add(minimumTemperature);
		}
		return maxTemperatures;
	}

	private List<RainFall> getListOfRainFallTemp(List<WeatherResult> temperatures) {
		List<RainFall> maxTemperatures = new ArrayList<>();
		for (WeatherResult weatherResult : temperatures){
			RainFall rainFall =new RainFall();
			rainFall.setMonth(weatherResult.getMonth());
			rainFall.setYear(weatherResult.getYear());
			rainFall.setValue(weatherResult.getValue());
			maxTemperatures.add(rainFall);
		}
		return maxTemperatures;
	}
}
