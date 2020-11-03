package com.example.brewbeers.network;

import com.example.brewbeers.models.MyPreciousResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BeerAPI {

    @GET("character")
    Call<MyPreciousResponse> getBeers(
//            @Query("abv_gt") Double alcoholByVolumeGreaterThan
//            sort=name:asc
//            limit
    );
//
//    @GET("v2/beers")
//    Call<BrewsResponse> getLesserBeers(
////            @Query("abv_lt") Double alcoholByVolumelessThan
//    );

}
