package com.example.lovermoneyptit.api;

import com.example.lovermoneyptit.models.Deal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("/money-lover/deals")
    Call<Void> syncDeal(@Body List<Deal> deals);

}
