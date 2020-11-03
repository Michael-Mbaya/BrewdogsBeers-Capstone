package com.example.brewbeers;

import android.util.Log;

import com.example.brewbeers.models.BeersModel;
import com.example.brewbeers.network.BeerAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tests {
    private void getResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BeerAPI api = retrofit.create(BeerAPI.class);
        Call<List<BeersModel>> call = api.getBeers();
        //
        call.enqueue(new Callback<List<BeersModel>>() {
            @Override
            public void onResponse(Call<List<BeersModel>> call, Response<List<BeersModel>> response) {
                List<BeersModel> beers = response.body();

                for (BeersModel b:beers){
                    Log.d("Name",b.getName());
                    Log.d("Tagline",b.getTagline());
                    Log.d("Contributed By",b.getContributedBy());
                }

            }

            @Override
            public void onFailure(Call<List<BeersModel>> call, Throwable t) {
                Log.d("on Failure","Failed attempted call");
                Log.d("on Failure", t.getMessage());
//                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }

        });

    }
}
