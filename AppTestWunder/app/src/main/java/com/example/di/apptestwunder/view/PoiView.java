package com.example.di.apptestwunder.view;

import com.example.di.apptestwunder.model.VehiclesList;

import retrofit2.Call;
import retrofit2.Response;


public interface PoiView {
    void showSuccess(Call<VehiclesList> call, Response<VehiclesList> response);
    void showFailure(Call<VehiclesList> call, Throwable t);
}
