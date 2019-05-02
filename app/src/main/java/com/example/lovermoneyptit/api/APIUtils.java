package com.example.lovermoneyptit.api;

public class APIUtils {

    private APIUtils() {}

    public static final String BASE_URL = "http://192.168.1.6:8080/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
