package com.siddharth.task.kissanhub.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Siddharth vanakudari.
 */

public interface APIInterface {

    @GET()
    public Call<List<WeatherResult>> doGetMinTemparature(@Url String url);

}
