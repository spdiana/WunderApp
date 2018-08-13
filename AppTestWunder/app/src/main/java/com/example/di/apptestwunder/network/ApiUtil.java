package com.example.di.apptestwunder.network;

public class ApiUtil {


    //public static final String BASE_URL = "https://fake-poi-api.mytaxi.com/";
    //public static final String BASE_URL = "https://raw.githubusercontent.com/";

    public static final String BASE_URL =  "https://s3-us-west-2.amazonaws.com/wunderbucket/";

    public static GetDataService getDataService(){
        return RetrofitInstance.getClient(BASE_URL).create(GetDataService.class);
    }
}
