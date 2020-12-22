package com.example.earthquakeretrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIcall {

    @GET("/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10")
    Call<Feature> response();
}
