package com.example.di.apptestwunder.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Vehicles {


    @SerializedName("address")
    private String address;

    @SerializedName("coordinates")
    public List<Double> coordinates;

    @SerializedName("engineType")
    private String engineType;

    @SerializedName("exterior")
    private String exterior;

    @SerializedName("fuel")
    private int fuel;

    @SerializedName("interior")
    private String interior;

    @SerializedName("name")
    private String name;

    @SerializedName("vin")
    private String vin;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
