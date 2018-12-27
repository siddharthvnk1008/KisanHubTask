
package com.siddharth.task.kissanhub.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siddharth.task.kissanhub.R;
import com.siddharth.task.kissanhub.model.Country;
import com.siddharth.task.kissanhub.model.Weather;

import java.util.List;

public class CountryWeatherAdapter extends ExpandableRecyclerAdapter<CountryWeatherViewHolder, WeatherViewHolder> {

    private LayoutInflater mInflator;

    public CountryWeatherAdapter(Context context, List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public CountryWeatherViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = mInflator.inflate(R.layout.movie_category_view, parentViewGroup, false);
        return new CountryWeatherViewHolder(view);
    }

    @Override
    public WeatherViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = mInflator.inflate(R.layout.weather_view, childViewGroup, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(CountryWeatherViewHolder countryWeatherViewHolder, int position, ParentListItem parentListItem) {
        Country country = (Country) parentListItem;
        countryWeatherViewHolder.bind(country);
    }

    @Override
    public void onBindChildViewHolder(WeatherViewHolder weatherViewHolder, int position, Object childListItem) {
        Weather Weather = (Weather) childListItem;
        weatherViewHolder.bind(Weather);
    }
}
