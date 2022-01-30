package com.example.double_recyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("sooryun_step.php")
    Call<List<RetroData>> getAllDatas();

//    @GET("sooryun_step_second.php")
//    Call<List<RetroData2>> getAllDatas2();

    @GET("sooryun_step_third.php")
    Call<List<RetroDataStep>> getAllDatas3();

    @GET("sooryun_filepath.php")
    Call<List<RetroFilePath>> getFilePathRetro();

}
