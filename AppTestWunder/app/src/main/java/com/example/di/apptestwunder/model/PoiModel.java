package com.example.di.apptestwunder.model;

import android.util.Log;

import com.example.di.apptestwunder.network.ApiUtil;
import com.example.di.apptestwunder.presenter.OnGetPoiListener;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PoiModel implements Callback<VehiclesList> {

    private OnGetPoiListener listener;

    public PoiModel(OnGetPoiListener listener) {
        this.listener = listener;
    }


    public void getFromBaseUrl(){
        //ApiUtil.getDataService().getInforData(53.694865, 9.757589, 53.394655, 10.099891).enqueue(this);
        ApiUtil.getDataService().getInforData().enqueue(this);

    }

    @Override
    public void onResponse(Call<VehiclesList> call, Response<VehiclesList> response) {
        Log.wtf("URL Called Model", call.request().url() + "");
        listener.onGetPoiSuccess(call, response);
    }

    @Override
    public void onFailure(Call<VehiclesList> call, Throwable t) {
        listener.onGetPoiFailed(call, t);
    }
}
