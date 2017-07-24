package com.example.android.test;

/**
 * Created by bdobre on 20/07/2017.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BikeResponse {

    @SerializedName("networks")
    @Expose
    private List<BikeLocation> bikes = null;

    public List<BikeLocation> getBikes() {
        return bikes;
    }

    public void setBikes(List<BikeLocation> bikes) {
        this.bikes = bikes;
    }
}
