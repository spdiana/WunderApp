package com.example.di.apptestwunder.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VehiclesList {

    @SerializedName("placemarks")
    private ArrayList<Vehicles> vehiclesList;

    public ArrayList<Vehicles> getVehiclesList() {
        return vehiclesList;
    }

    public void setVehiclesList(ArrayList<Vehicles> vehiclesList) {
        this.vehiclesList = vehiclesList;
    }



}
