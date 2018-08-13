package com.example.di.apptestwunder.presenter;


import com.example.di.apptestwunder.model.VehiclesList;


import retrofit2.Call;
import retrofit2.Response;


public interface OnGetPoiListener {
    void onGetPoiSuccess(Call<VehiclesList> call, Response<VehiclesList> response);
    void onGetPoiFailed(Call<VehiclesList> call, Throwable t);
}
