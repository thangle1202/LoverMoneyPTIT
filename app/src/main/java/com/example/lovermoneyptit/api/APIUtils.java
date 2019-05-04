package com.example.lovermoneyptit.api;

public class APIUtils {

    private APIUtils() {}

    public static final String BASE_URL = "http://192.168.56.1:8080/";

    public static MoneyService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(MoneyService.class);
    }

}
