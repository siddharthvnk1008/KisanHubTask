package com.siddharth.task.kissanhub.ui;

import android.view.View;
import android.widget.TextView;

import com.siddharth.task.kissanhub.R;
import com.siddharth.task.kissanhub.model.Weather;
import com.siddharth.task.kissanhub.util.Utility;

public class WeatherViewHolder extends ChildViewHolder {

    private TextView mMaxTempTextView;
    private TextView mMinTempTextView;
    private TextView mRainfallTextView;

    private TextView mMaxTempDateTextView;
    private TextView mMinTempDateTextView;
    private TextView mRainfallDateTextView;




    public WeatherViewHolder(View itemView) {
        super(itemView);
        mMaxTempDateTextView = (TextView) itemView.findViewById(R.id.tv_max_month);
        mMinTempDateTextView = (TextView) itemView.findViewById(R.id.tv_min_month);
        mRainfallDateTextView = (TextView) itemView.findViewById(R.id.tv_rain_month);

        mMaxTempTextView = (TextView) itemView.findViewById(R.id.tv_max_temp);
        mMinTempTextView = (TextView) itemView.findViewById(R.id.tv_min_temp);
        mRainfallTextView = (TextView) itemView.findViewById(R.id.tv_rainfall);
    }

    public void bind(Weather weather) {
        mMaxTempDateTextView.setText(String.valueOf(Utility.getMonth(String.valueOf(weather.getMaxTemperature().getMonth()))+" "+weather.getMaxTemperature().getYear()));
        mMinTempDateTextView.setText(String.valueOf(Utility.getMonth(String.valueOf(weather.getMinimumTemperature().getMonth()))+" "+weather.getMinimumTemperature().getYear()));
        mRainfallDateTextView.setText(String.valueOf(Utility.getMonth(String.valueOf(weather.getRainFall().getMonth()))+" "+weather.getRainFall().getYear()));

        mMaxTempTextView.setText(weather.getMaxTemperature().getValue());
        mMinTempTextView.setText(weather.getMinimumTemperature().getValue());
        mRainfallTextView.setText(weather.getRainFall().getValue());
    }
}
