package com.example.double_recyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("sooryun_step.php")
    Call<List<RetroData>> getAllDatas();

    @GET("sooryun_step_second.php")
    Call<List<RetroData2>> getAllDatas2();

}
