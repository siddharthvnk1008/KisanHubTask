package com.siddharth.task.kissanhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.siddharth.task.kissanhub.domain.WeatherActivityContract;
import com.siddharth.task.kissanhub.domain.WeatherPresenter;
import com.siddharth.task.kissanhub.model.WeatherViewModel;
import com.siddharth.task.kissanhub.ui.CountryWeatherAdapter;
import com.siddharth.task.kissanhub.ui.ExpandableRecyclerAdapter;

public class WeatherActivity extends AppCompatActivity implements WeatherActivityContract.View{

    private CountryWeatherAdapter mAdapter;
    private RecyclerView recyclerView;
    private WeatherActivityContract.Presenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.simpleProgressBar);
        setTitle("Weather Info");
        presenter = new WeatherPresenter();
        presenter.init(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter!=null){
            presenter.loadWeatherData();
        }
    }

    @Override
    public void showWeatherData(final WeatherViewModel weatherViewModel) {
        progressBar.setVisibility(View.GONE);

        mAdapter = new CountryWeatherAdapter(this, weatherViewModel.getCountryList());
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
            }

            @Override
            public void onListItemCollapsed(int position) {

            }
        });

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}