package com.example.earthquakeretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
//base url = "https://earthquake.usgs.gov/"

    RecyclerView recyclerView;
    List<Properties> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIcall callData =retrofit.create(APIcall.class);

        callData.response().enqueue(new Callback<Feature>() {
            @Override
            public void onResponse(Call<Feature> call, Response<Feature> response) {

                Feature dataModel = response.body();
                data = new ArrayList<>(Arrays.asList(dataModel.getFeatures()));
                Log.i("firstData",data.toString());
                putData(data);
            }

            @Override
            public void onFailure(Call<Feature> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void putData(List<Properties> data) {
        AdapterClass adapterClass = new AdapterClass(this,data,R.layout.earthquake_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterClass);
    }
}