package com.example.lovermoneyptit.api;

import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.models.Wallet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MoneyService {

    @POST("/money-lover/deals")
    Call<Void> syncDeal(@Body List<Deal> deals);

    @GET("/money-lover/deals/{userId}")
    Call<List<Deal>> getDealByUserId(@Path("userId") int userId);

    @PUT("/money-lover/deal")
    Call<Deal> editDeal(@Body Deal deal);

    @POST("/money-lover/wallets")
    Call<Wallet> syncWallet(@Body List<Wallet> wallets);

    @POST("/money-lover/groups")
    Call<Group> syncGroup(@Body List<Group> groups);

    @GET("/money-lover/wallets")
    Call<List<Wallet>> getAllWalletFromServer();

    @GET("/money-lover/groups")
    Call<List<Group>> getAllGroupFromServer();

}
