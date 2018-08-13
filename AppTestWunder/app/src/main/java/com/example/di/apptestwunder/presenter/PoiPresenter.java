package com.example.di.apptestwunder.presenter;



import com.example.di.apptestwunder.model.PoiModel;
import com.example.di.apptestwunder.model.VehiclesList;
import com.example.di.apptestwunder.view.PoiView;

import retrofit2.Call;
import retrofit2.Response;


public class PoiPresenter implements OnGetPoiListener {
    private PoiView view;
    private PoiModel model;

    public PoiPresenter(PoiView view) {
        this.view = view;
        model = new PoiModel(this);

    }

    public void getFromBaseUrl(){
        model.getFromBaseUrl();
    }

    @Override
    public void onGetPoiSuccess(Call<VehiclesList> call, Response<VehiclesList> response) {
        view.showSuccess(call, response);
    }

    @Override
    public void onGetPoiFailed(Call<VehiclesList> call, Throwable t) {
        view.showFailure(call, t);
    }
}
