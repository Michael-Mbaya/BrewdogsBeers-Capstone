package com.example.brewbeers.network;

import com.example.brewbeers.models.BeersModel;
import com.example.brewbeers.models.prev.MyPreciousResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BeerAPI {

    @GET("character")
    Call<MyPreciousResponse> getChars(
//            @Query("abv_gt") Double alcoholByVolumeGreaterThan
//            sort=name:asc
//            limit
    );

    @GET("beers")
    Call<List<BeersModel>> getBeers(
//        @Query("beer_name") String beerNameQuery,
//        @Query("abv_gt") Double alcoholByVolumeGreaterThan
    );
}
