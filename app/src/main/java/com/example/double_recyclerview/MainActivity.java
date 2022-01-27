package com.example.double_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<RetroData> retroDataList;
    SubItemAdapter subItemAdapter;
    public RetroData retroData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //RetroDataStep & retrofit2 결합.
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroData>> call = getDataService.getAllDatas();

        call.enqueue(new Callback<List<RetroData>>() {
            @Override
            public void onResponse(Call<List<RetroData>> call, Response<List<RetroData>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    // 통신되면.
                    generateDataList(response.body());
                    Toast.makeText(getApplicationContext(), "잘했어.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<RetroData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "도망쳐.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void generateDataList(List<RetroData> retroDataList) {
        // 상위 리사이클러뷰 설정
        RecyclerView rvItem = findViewById(R.id.rv_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        ItemAdapter itemAdapter = new ItemAdapter(retroDataList);
        rvItem.setAdapter(itemAdapter);
        rvItem.setLayoutManager(layoutManager);
    }


}