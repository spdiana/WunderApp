package com.example.di.apptestwunder.network;


import com.example.di.apptestwunder.model.VehiclesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GetDataService {

    //https://fake-poi-api.mytaxi.com/?p1Lat=53.694865&p1Lon=9.757589&p2Lat=53.394655&p2Lon=10.099891
    //It's no solving the json url, so I moved the json to my githubb url.
    @GET("./")
    Call<VehiclesList> getInforData(@Query("p1Lat") double p1Lat, @Query("p1Lon") double p1Lon,
                                    @Query("p2Lat") double p2Lat, @Query("p2Lon") double p2Lon);


    @GET("locations.json")
    Call<VehiclesList> getInforData();

}
